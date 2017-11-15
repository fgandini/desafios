package org.gandini.robot.reddit.response;


import org.gandini.robot.reddit.response.commands.Ajuda;
import org.gandini.robot.reddit.response.commands.NadaPraFazer;

import java.util.Optional;

public class TextResponseFactory {

    private final String UNKNOW = "Acho que eu n\u00E3o entendi!\nEscreva \\ajuda para receber minha lista de comandos.";

    public TextResponseFactory() {
    }

    public String getResponse(String message) {
        String[] strings = message.split(" ");
        String command = strings[0].toUpperCase().replace("/", "");
        String args = strings.length > 1 ? strings[1] : "";
        Optional<TextResponseInterface> responseProvider = responseProvider(command);
        if (responseProvider.isPresent()) {
            return responseProvider.get().getResponseText(args);
        } else {
            return UNKNOW;
        }
    }

    private Optional<TextResponseInterface> responseProvider(String command) {
        switch (command) {
            case "AJUDA":
                return Optional.of(new Ajuda());
            case "NADAPRAFAZER":
                return Optional.of(new NadaPraFazer());
            default:
                return Optional.empty();
        }
    }
}
