package org.gandini.robot.reddit;

import org.gandini.robot.reddit.response.TextResponseFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class RedditBotProvider extends TelegramLongPollingBot {

    private final String username;
    private final String token;

    private final String ONLY_TEXT = "Ei pessoa! Só funciono com texto!";

    public RedditBotProvider(String username, String token) {
        this.username = username;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String response = "";
        //se for uma imagem ou audio
        if (update.hasMessage() && !update.getMessage().hasText()) {
            response = ONLY_TEXT;
            //se for texto
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            response = new TextResponseFactory().getResponse(update.getMessage().getText());
        }

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(update.getMessage().getChatId())
                .disableWebPagePreview()
                .setText(response);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
