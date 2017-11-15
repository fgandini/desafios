package org.gandini.api.resource;

import org.gandini.api.configuration.ApplicationConfiguration;
import org.gandini.api.runner.Reddit;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/crawler")
@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class CrawlerResource {

    private ApplicationConfiguration configuration;

    public CrawlerResource(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Path("/reddit/hot")
    public Response getHot(
            @DefaultValue("5000") @Min(0) @QueryParam("minimo-votos") int minimoVotos,
            @QueryParam("subreddits") @NotEmpty(message = "não pode estar vazio.") String subreddits) {

        return Response.ok()
                .entity(new Reddit(configuration.getRedditURL())
                        .fetchHot(Arrays.asList(subreddits.split(";")), minimoVotos)).build();
    }
}
