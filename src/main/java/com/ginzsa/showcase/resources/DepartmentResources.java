package com.ginzsa.showcase.resources;

import com.ginzsa.showcase.model.Department;
import com.ginzsa.showcase.model.Employee;
import com.ginzsa.showcase.repo.DepartmentDao;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by santiago.ginzburg on 2/16/16.
 */
@Path("department")
public class DepartmentResources {

    private DepartmentDao departmentDao;

    @Inject
    public DepartmentResources(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @DELETE
    public Response deleteAll() {
        departmentDao.removeAll();
        return Response.ok().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("list")
    public Response getAll() {
        List<Department> list = departmentDao.getAll();
        GenericEntity<List<Department>> entity = new GenericEntity<List<Department>>(list) {};
        return Response
                .ok(entity)
                .build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    public Department getById(@PathParam("id") Long id) {
        return departmentDao.getById(id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Department create(Department department) {
        this.departmentDao.save(department);
        return department;
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Department updateEmployee(Department department) {
        this.departmentDao.upadate(department);
        return  department;
    }
}