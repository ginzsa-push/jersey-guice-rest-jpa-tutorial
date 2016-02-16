package com.ginzsa.showcase.resources;

import com.ginzsa.showcase.model.Employee;
import com.ginzsa.showcase.repo.EmployeeDao;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by santiago.ginzburg on 2/16/16.
 */
@Path("employee")
public class EmployeeResources {

    private EmployeeDao employeeDao;

    @Inject
    public EmployeeResources(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @DELETE
    public Response deleteEmployees() {
        employeeDao.removeAll();
        return Response.ok().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("list")
    public Response getAll() {
        List<Employee> list = employeeDao.getAll();
        GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(list) {};
        return Response
                .ok(entity)
                .build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    public Employee getById(@PathParam("id") Long id) {
        return employeeDao.getById(id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Employee create(Employee employee) {
        this.employeeDao.save(employee);
        return employee;
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Employee updateEmployee(Employee employee) {
        this.employeeDao.upadate(employee);
        return  employee;
    }
}