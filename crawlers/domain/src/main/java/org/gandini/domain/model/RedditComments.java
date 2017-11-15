package org.gandini.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedditComments {

    @JsonProperty("link")
    public String link;

    @JsonProperty("count")
    public String count;

    public RedditComments() {
    }
}
