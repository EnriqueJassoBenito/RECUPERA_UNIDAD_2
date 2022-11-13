package mx.edu.utez.recuperau2.model.students;

import mx.edu.utez.recuperau2.model.Repository;
import mx.edu.utez.recuperau2.model.calificacion.BeanCalificacion;
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

public class DaoStudent implements Repository<BeanStudent> {

    Connection conn;

    PreparedStatement ps;

    ResultSet rs;

    MySQL students = new MySQL();

    @Override
    public List<BeanStudent> findAll() {
        List<BeanStudent> Students = new ArrayList<>();
        BeanStudent student = null;
        try{
            conn = students.getConnection();
            String query = "SELECT * FROM students;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setLastname(rs.getString("lastname"));
                student.setBirthday(rs.getString("birthday"));
                student.setCurp(rs.getString("curp"));
                student.setEnrollment(rs.getString("enrollment"));
                Students.add(student);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            students.close(conn,ps,rs);
        }
        return Students;
    }

    @Override
    public BeanStudent findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanStudent> save(BeanStudent student) {
        try{

            if(validacionMatricula(student.getEnrollment())){
                return new Response<BeanStudent>(400,"No se puede repertir la misma matricula",student,true);
            }

            conn = students.getConnection();
            String query = "INSERT INTO students (name,surname,lastname,birthday,curp,enrollment)"+"VALUES(?,?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setString(1,student.getName());
            ps.setString(2,student.getSurname());
            ps.setString(3,student.getLastname());
            ps.setString(4,student.getBirthday());
            ps.setString(5,student.getCurp());
            ps.setString(6,student.getEnrollment());
            if (ps.executeUpdate() == 1){
                return new Response<>(200,"Registrado correctamente", student, false);
            }else{
                return new Response<>(200,"Error al registrar",student,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: " + e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        }finally{
            students.close(conn,ps,rs);
        }
    }

    private boolean validacionMatricula(String enrollment) {
        try{
            conn = students.getConnection();
            String query = "SELECT * FROM students WHERE enrollment = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, enrollment);
            rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> validacionMatricula: "+e.getMessage());
        }finally {
            students.close(conn,ps,rs);
        }
        return false;
    }

    @Override
    public Response<BeanStudent> update(BeanStudent student) {
        try{
            conn = students.getConnection();
            String query = "UPDATE students set name=?,surname=?,lastname=?,birthday=?,curp=?,enrollment=? WHERE id=?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,student.getName());
            ps.setString(2,student.getSurname());
            ps.setString(3,student.getLastname());
            ps.setString(4,student.getBirthday());
            ps.setString(5,student.getCurp());
            ps.setString(6,student.getEnrollment());
            ps.setLong(7,student.getId());
            if (ps.executeUpdate() == 1){
                return new Response<>(200,"Actualizado correctamente", student, false);
            }else{
                return new Response<>(400,"Error al registrar",student,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        }finally {
            students.close(conn,ps,rs);
        }
    }

    public List<BeanCalificacion> promedio (){
        List<BeanCalificacion> calificacions = new ArrayList<>();
        BeanCalificacion calificacion = null;
        try{
            conn = students.getConnection();
            String query = "SELECT AVG(qualification) as Promedio_General FROM qualifications;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                calificacion = new BeanCalificacion();
                calificacion.setQualification(rs.getDouble("Promedio_General"));
                //calificacion.setQualification(qualification);
                calificacions.add(calificacion);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            students.close(conn,ps,rs);
        }
        return calificacions;
    }

    @Override
    public Response<BeanStudent> remove(Long id) {
        return null;
    }
}
