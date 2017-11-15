package org.gandini.api;


import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.gandini.api.configuration.ApplicationConfiguration;
import org.gandini.api.resource.CrawlerResource;


public class Main extends Application<ApplicationConfiguration> {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void run(ApplicationConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new CrawlerResource(configuration));
    }

}