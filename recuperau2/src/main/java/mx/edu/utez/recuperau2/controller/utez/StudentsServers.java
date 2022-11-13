package mx.edu.utez.recuperau2.controller.utez;

import mx.edu.utez.recuperau2.model.students.BeanStudent;
import mx.edu.utez.recuperau2.model.students.DaoStudent;
import mx.edu.utez.recuperau2.model.teachers.BeanTeacher;
import mx.edu.utez.recuperau2.model.teachers.DaoTeacher;
import mx.edu.utez.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/student")
public class StudentsServers {

    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanStudent> getAll(){
        return new DaoStudent().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanStudent getById(@PathParam("id") Long id){
        return new DaoStudent().findById(id);
    };

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save(BeanStudent student){
        System.out.println(student.getName());
        Response<BeanStudent> result = new DaoStudent().save(student);
        response.put("result", result);
        return response;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update(BeanStudent student){
        System.out.println(student.getName());
        Response<BeanStudent> result = new DaoStudent().update(student);
        response.put("result", result);
        return response;
    }
}
