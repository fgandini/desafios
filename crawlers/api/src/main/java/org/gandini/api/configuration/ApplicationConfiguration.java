package org.gandini.api.configuration;

import io.dropwizard.Configuration;

public class ApplicationConfiguration extends Configuration {

    private String redditURL;

    public String getRedditURL() {
        return redditURL;
    }
}
