package org.gandini.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedditMain {

    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @JsonProperty("subreddit")
    public String subreddit;

    @JsonProperty("erro")
    public String erro;

    @JsonProperty("threads")
    public List<RedditThread> threads = new ArrayList<>();

    public RedditMain(String subreddit) {
        this.subreddit = subreddit;
    }

    public RedditMain() {
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            try {
                return objectMapper.writeValueAsString(new RedditMain());
            } catch (JsonProcessingException e1) {
                super.toString();
            }
        }
        return "";
    }
}