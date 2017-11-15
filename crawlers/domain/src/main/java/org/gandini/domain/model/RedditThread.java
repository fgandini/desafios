package org.gandini.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedditThread {

    @JsonProperty("title")
    public String title;

    @JsonProperty("votes")
    public int votes;

    @JsonProperty("self_link")
    public String link;

    @JsonProperty("subreddit")
    public String subreddit;

    @JsonProperty("comments")
    public RedditComments comments = new RedditComments();

    public RedditThread() {
    }

    public int getVotes() {
        return this.votes;
    }
}