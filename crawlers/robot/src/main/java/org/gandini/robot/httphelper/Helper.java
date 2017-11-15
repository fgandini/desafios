package org.gandini.robot.httphelper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Helper {

    private final OkHttpClient client;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Helper() {
        this.client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public Response get(String endpoint, int minimumScore, String subreddits) throws IOException {

        logger.info("Fazendo chamada get para {}", endpoint);
        Request request = new Request.Builder()
                .url(endpoint + "?minimo-votos=" + minimumScore + "&subreddits=" + subreddits)
                .build();
        return requester(request);
    }

    private Response requester(Request request) throws IOException {
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            logger.error("Falha ao realizar a requisicao.", e);
            throw e;
        }
    }
}
