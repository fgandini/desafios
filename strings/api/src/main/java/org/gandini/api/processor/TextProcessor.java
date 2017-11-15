package org.gandini.api.processor;


import org.gandini.api.domain.TextInputObject;
import org.gandini.api.domain.TextOutputObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TextProcessor {

    private TextInputObject textInputObject;

    public TextProcessor(TextInputObject textInputObject) {
        this.textInputObject = textInputObject;
    }

    public String processText() {
        String wrapedText = wrapText();
        if (textInputObject.justify) {
            return justify(wrapedText);
        }
        return wrapedText;
    }

    public TextOutputObject processJson() {
        String wrapedText = wrapText();
        if (textInputObject.justify) {
            wrapedText = justify(wrapedText);
        }
        return new TextOutputObject(wrapedText.replaceAll("\"", "'"));
    }

    private String wrapText() {
        //existe uma lib (apache.commons.lang.WordUtils) que faz isso, mas imagino que não seja o proposito do desafio.
        //String wrapedText = wrap(normalize(textInputObject.text), textInputObject.lineLength);

        String[] parts = normalize(textInputObject.text).split(" ");

        Integer charPossition = 0;
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (charPossition + part.length() < textInputObject.lineLength) {
                if (System.lineSeparator().equals(part)) {
                    charPossition = 0;
                } else if (charPossition == 0) {
                    charPossition += part.length();
                } else {
                    sb.append(" ");
                    charPossition += part.length() + 1;
                }
            } else {
                sb.append(System.lineSeparator());
                charPossition = part.length();
            }
            sb.append(part);
        }
        return sb.toString();
    }

    private String justify(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(text.split(System.lineSeparator())).forEach(l -> {
            if (l.length() < textInputObject.lineLength && !l.equals(System.lineSeparator()) && !l.isEmpty()) {
                stringBuilder.append(addSpace(l));
            } else {
                stringBuilder.append(l);
            }
            stringBuilder.append(System.lineSeparator());

        });
        return stringBuilder.toString();
    }

    private String addSpace(String line) {
        //caso não acabe com o caracter de quebra de linha, coloca um incremento
        int incr = line.charAt(line.length() - 1) == 10 ? 0 : 1;
        //para evitar casos onde uma linha só tem duas palavras e cada uma fica em uma extremidade do texto
        double porcentage = (double) (line.length() + incr) / (double) textInputObject.lineLength;
        if (porcentage < textInputObject.elasticity.get() && applyElasticity(line)) {
            return line;
        }
        //regex para quebrar por espacos mas mante-los no vetor (pega no stackoverflow)
        String[] breakLine = line.split("((?<=\\s+)|(?=\\s+))");
        if (breakLine.length == 1 || !line.contains(" ")) {
            return stringfy(breakLine);
        }

        int diference = textInputObject.lineLength - (line.length() + incr);

        while (diference >= 0) {
            for (int i = 0; i < breakLine.length; i++) {
                if (diference >= 0) {
                    if (breakLine[i].contains(" ")) {
                        breakLine[i] = breakLine[i] + " ";
                        diference--;
                    }
                }
            }
        }
        return stringfy(breakLine);
    }

    private boolean applyElasticity(String line) {
        return line.matches(".*[!\\?^.]$");
    }

    private String normalize(String text) {
        return text.replaceAll(" +", " ")
                .replaceAll("\\r\\n", " " + System.lineSeparator() + " ")
                .replaceAll("\\n", " " + System.lineSeparator() + " ");
    }

    private String stringfy(String[] words) {
        return stringfy(Arrays.asList(words));
    }

    private String stringfy(List<String> lines) {
        String string = "";
        for (String s : lines) {
            string += s;
        }
        return string;
    }
}