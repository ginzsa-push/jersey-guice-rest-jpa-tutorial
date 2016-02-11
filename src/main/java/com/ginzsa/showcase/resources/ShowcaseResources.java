package com.ginzsa.showcase.resources;

import com.ginzsa.showcase.model.Showcase;
import com.ginzsa.showcase.repo.Dao;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
@Path("showcase")
public class ShowcaseResources {

    private Dao<Showcase> showcaseDao;

    @Inject
    public ShowcaseResources(Dao<Showcase> dao) {
        this.showcaseDao = dao;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAll() {
        List<Showcase> list = showcaseDao.getAll();
        GenericEntity<List<Showcase>> entity = new GenericEntity<List<Showcase>>(list) {};
        return Response
                .ok(entity)
                .build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    public Showcase getById(@PathParam("id") Long id) {
        return showcaseDao.getById(id);
    }
}