package org.gandini.api.resource;


import org.gandini.api.domain.Errors;
import org.gandini.api.domain.TextInputObject;
import org.gandini.api.domain.TextOutputObject;
import org.gandini.api.domain.Validator;
import org.gandini.api.processor.TextProcessor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/texto")
@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class StringManipulationResource {

    @POST
    @Path("/formartar")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response format(TextInputObject textInputObject, @HeaderParam("Accept") String accept) {
        //por causa do retorno em text/plain nao consegui usar os validadores do javax e hibernate direto no bean. :(
        Optional<Errors> errors = Validator.validate(textInputObject);
        if (errors.isPresent()) {
            return Response.status(422).entity(errors.get().toString()).build();
        }

        if (accept.equals(MediaType.APPLICATION_JSON)) {
            TextOutputObject formatedTextJson = new TextProcessor(textInputObject).processJson();
            return Response.ok().entity(formatedTextJson).build();
        }

        String formatedText = new TextProcessor(textInputObject).processText();
        return Response.ok().entity(formatedText).build();
    }
}
