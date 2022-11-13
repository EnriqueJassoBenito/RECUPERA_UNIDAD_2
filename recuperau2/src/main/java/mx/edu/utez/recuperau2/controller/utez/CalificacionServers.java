package mx.edu.utez.recuperau2.controller.utez;

import mx.edu.utez.recuperau2.model.calificacion.BeanCalificacion;
import mx.edu.utez.recuperau2.model.calificacion.DaoCalificacion;
import mx.edu.utez.recuperau2.model.students.BeanStudent;
import mx.edu.utez.recuperau2.model.students.DaoStudent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/calificacion")
public class CalificacionServers {

    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanCalificacion> getAll(){
        return new DaoCalificacion().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanCalificacion getById(@PathParam("id") Long id){
        return new DaoCalificacion().findById(id);
    };

}
