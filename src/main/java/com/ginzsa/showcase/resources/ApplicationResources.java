package com.ginzsa.showcase.resources;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by santiago.ginzburg on 2/16/16.
 */
@Path("healthcheck")
public class ApplicationResources {

    public Response healthcheck() {
        return Response.ok().build();
    }
}
