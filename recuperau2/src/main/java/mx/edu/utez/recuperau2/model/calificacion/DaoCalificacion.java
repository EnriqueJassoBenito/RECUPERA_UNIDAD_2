package mx.edu.utez.recuperau2.model.calificacion;

import mx.edu.utez.recuperau2.model.Repository;
import mx.edu.utez.recuperau2.model.students.BeanStudent;
import mx.edu.utez.recuperau2.model.teachers.BeanTeacher;
import mx.edu.utez.recuperau2.model.teachers.DaoTeacher;
import mx.edu.utez.recuperau2.utils.MySQL;
import mx.edu.utez.recuperau2.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoCalificacion implements Repository<BeanCalificacion> {

    Connection conn;

    PreparedStatement ps;

    ResultSet rs;

    MySQL calificaciones = new MySQL();


    @Override
    public List<BeanCalificacion> findAll() {
        List<BeanCalificacion> calificacions = new ArrayList<>();
        BeanCalificacion calificacion = null;
        BeanStudent student = null;
        try{
            conn = calificaciones.getConnection();
            String query = "SELECT * FROM qualifications;";
                    //"SELECT s.name, q.qualification, q.subjects FROM qualifications q INNER JOIN students s ON q.id_student = q.id;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                calificacion = new BeanCalificacion();
                calificacion.setId(rs.getLong("id"));
                calificacion.setSubjects(rs.getString("subjects"));
                calificacion.setQualification(rs.getDouble("qualification"));
                student.setId(rs.getLong("id"));
                calificacion.setId_student(student);
                calificacions.add(calificacion);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            calificaciones.close(conn,ps,rs);
        }
        return calificacions;
    }

    @Override
    public BeanCalificacion findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanCalificacion> save(BeanCalificacion object) {
        return null;
    }

    @Override
    public Response<BeanCalificacion> update(BeanCalificacion object) {
        return null;
    }

    @Override
    public Response<BeanCalificacion> remove(Long id) {
        return null;
    }
}
