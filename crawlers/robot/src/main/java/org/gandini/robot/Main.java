package org.gandini.robot;


import org.gandini.robot.configuration.Config;
import org.gandini.robot.reddit.RedditBotProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        parseArgs(args);
        Config.load(args[0]);
        logger.info("Robo do telegram + reddit iniciado.");
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new RedditBotProvider(Config.botConfig.botName, Config.botConfig.getToken()));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private static void parseArgs(String[] args) {

        if (args.length == 0 || !args[0].contains("config.yml")) {
            logger.error("O primeiro argumento precisa ser o caminho para o arquivo config.yml");
            System.exit(0);

        }
    }
}