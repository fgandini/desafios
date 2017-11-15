package org.gandini.robot.configuration;

public class BotConfig {

    public String apiEndpoint;
    public String botName;
    public String token;
    public int minimumScore;

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public String getBotName() {
        return botName;
    }

    public String getToken() {
        return token;
    }

    public int getMinimumScore() {
        return minimumScore;
    }
}
