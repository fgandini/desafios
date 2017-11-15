package org.gandini.api.runner;


import org.gandini.domain.model.RedditMain;
import org.gandini.domain.model.RedditThread;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reddit {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String baseURL;

    public Reddit(String baseURL) {
        this.baseURL = baseURL;
    }

    public List<RedditMain> fetchHot(List<String> subreddits, int minimumVotes) {
        List<RedditMain> listRedditMain = new ArrayList<>();
        for (String subreddit : subreddits) {
            try {
                listRedditMain.add(fetchHot(subreddit, minimumVotes));
            } catch (IOException e) {
                logger.error("Falha ao tentar buscar dados do subreddit : {} ", subreddit);
            }
        }
        return listRedditMain;
    }

    public RedditMain fetchHot(String subreddit, int minimumVotes) throws IOException {
        RedditMain redditMain = new RedditMain(subreddit);
        Document document;
        try {
            document = Jsoup.connect(baseURL + "/r/" + subreddit).get();
        } catch (HttpStatusException e) {
            redditMain.erro = "Não encontrada :(";
            return redditMain;
        }
        Elements elements = document.getElementsByClass("thing");
        redditMain.threads = elements.parallelStream()
                .filter(e -> Integer.parseInt(e.attr("data-score")) > minimumVotes)
                .map(this::fetchThread)
                .collect(Collectors.toList());
        return redditMain;
    }

    private RedditThread fetchThread(Element element) {
        RedditThread redditThread = new RedditThread();
        redditThread.title = element.selectFirst("a.title").text();
        redditThread.votes = Integer.parseInt(element.attr("data-score"));
        redditThread.link = element.attr("data-url");
        redditThread.subreddit = element.attr("data-subreddit");
        redditThread.comments.count = element.attr("data-comments-count");
        redditThread.comments.link = baseURL.concat(element.attr("data-permalink"));
        return redditThread;
    }
}
