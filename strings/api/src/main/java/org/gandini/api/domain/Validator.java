package org.gandini.api.domain;


import java.util.Optional;

public class Validator {

    public static Optional<Errors> validate(TextInputObject textInputObject) {
        Errors errors = new Errors();
        if (textInputObject.text.isEmpty()) {
            errors.erros.add("Texto não pode ser vazio.");
        }
        if (textInputObject.lineLength < 1) {
            errors.erros.add("Tamanho da linha tem que ser maior ou igual a 1.");
        }
        if (textInputObject.elasticity.isPresent() &&
                textInputObject.elasticity.get() < 0 || textInputObject.elasticity.get() > 1) {
            errors.erros.add("Elasticidade tem que estar entre 0 e 1 (default 0.8).");
        }
        if (!errors.erros.isEmpty()) {
            return Optional.of(errors);
        }
        return Optional.empty();
    }
}
