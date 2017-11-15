package org.gandini.robot.reddit.response.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.gandini.domain.model.RedditMain;
import org.gandini.domain.model.RedditThread;
import org.gandini.robot.configuration.Config;
import org.gandini.robot.httphelper.Helper;
import org.gandini.robot.reddit.response.TextResponseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class NadaPraFazer implements TextResponseInterface {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String ERROR = "Parece que n\u00E3o encontramos nada para esse subreddit!";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getResponseText(String args) {
        try {
            Response response = new Helper().get(Config.botConfig.apiEndpoint, Config.botConfig.minimumScore, args);
            List<RedditMain> threadsList = objectMapper.readValue(response.body().string(),
                    new TypeReference<List<RedditMain>>() {
            });

            String result = format(threadsList);
            if (result.isEmpty()) {
                return ERROR;
            }
            return result;

        } catch (IOException e) {
            logger.error("Falha ao tentar recuperar as threads no Reddit.", e);
        }
        return ERROR;
    }

    @Override
    public String getHelp(String args) {
        return null;
    }

    private String format(List<RedditMain> threadsList) {
        StringBuilder sb = new StringBuilder();
        threadsList.forEach(c ->
                c.threads.forEach(t -> sb.append(formatThread(t)))
        );
        return sb.toString();
    }

    private String formatThread(RedditThread redditThread) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("Subreddit: ").append(redditThread.subreddit).append(System.lineSeparator());
        sb.append("Titulo: ").append(redditThread.title).append(System.lineSeparator());
        sb.append("Votos: ").append(redditThread.votes).append(System.lineSeparator());
        sb.append("link: ").append(redditThread.link).append(System.lineSeparator());
        sb.append("Comentarios: ").append(redditThread.comments.link).append(System.lineSeparator());
        sb.append(System.lineSeparator());
        return sb.toString();
    }


}
