package com.ginzsa.showcase.resources;

import com.ginzsa.showcase.model.Showcase;
import com.ginzsa.showcase.repo.Dao;
import com.ginzsa.showcase.repo.ShowcaseDao;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
@Path("showcase")
public class ShowcaseResources {

    private ShowcaseDao showcaseDao;

    @Inject
    public ShowcaseResources(ShowcaseDao dao) {
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

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Showcase create(Showcase showcase) {
        this.showcaseDao.save(showcase);
        return showcase;
    }
}