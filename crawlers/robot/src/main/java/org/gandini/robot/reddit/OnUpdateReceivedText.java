package org.gandini.robot.reddit;

import org.telegram.telegrambots.api.objects.Update;

public class OnUpdateReceivedText {

    private final Update update;

    private final String HELP = "Acho que eu não entendi!\nEscreva /ajuda para receber minha lista de comandos.";

    public OnUpdateReceivedText(Update update) {
        this.update = update;
    }

    public String getResponse() {

        String response = "";
        if (update.hasMessage() && !update.getMessage().hasText()) {
            return HELP;
        } else if (update.hasMessage() && update.getMessage().hasText()) {

            response = update.getMessage().getText();
        }
        return response;
    }
}
