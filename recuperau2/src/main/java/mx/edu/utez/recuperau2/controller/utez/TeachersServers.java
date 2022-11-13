package mx.edu.utez.recuperau2.controller.utez;


import mx.edu.utez.recuperau2.model.teachers.BeanTeacher;
import mx.edu.utez.recuperau2.model.teachers.DaoTeacher;
import mx.edu.utez.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/utez")
public class TeachersServers {

    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanTeacher> getAll(){
        return new DaoTeacher().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanTeacher getById(@PathParam("id") Long id){
        return new DaoTeacher().findById(id);
    };

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save(BeanTeacher product){
        System.out.println(product.getName());
        Response<BeanTeacher> result = new DaoTeacher().save(product);
        response.put("result", result);
        return response;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update(BeanTeacher teacher){
        System.out.println(teacher.getName());
        Response<BeanTeacher> result = new DaoTeacher().update(teacher);
        response.put("result", result);
        return response;
    }

}
