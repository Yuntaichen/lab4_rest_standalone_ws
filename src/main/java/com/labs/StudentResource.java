package com.labs;

import java.util.LinkedHashSet;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/students")
@Produces({MediaType.APPLICATION_JSON})

public class StudentResource {
    @GET
    public LinkedHashSet<Student> getStudents(@QueryParam("name") String name) {
        LinkedHashSet<Student> students = new PostgreSQLDAO().getStudentsByFields(name);
        return students;
    }
}