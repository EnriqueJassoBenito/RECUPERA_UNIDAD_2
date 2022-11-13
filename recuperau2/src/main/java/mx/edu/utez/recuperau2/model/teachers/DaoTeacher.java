package mx.edu.utez.recuperau2.model.teachers;

import mx.edu.utez.recuperau2.model.Repository;
import mx.edu.utez.recuperau2.utils.MySQL;
import mx.edu.utez.recuperau2.utils.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoTeacher implements Repository<BeanTeacher>{

    Connection conn;

    PreparedStatement ps;

    ResultSet rs;

    MySQL teachers = new MySQL();


    @Override
    public List<BeanTeacher> findAll() {
        List<BeanTeacher> Teachers = new ArrayList<>();
        BeanTeacher teacher = null;
        try{
            conn = teachers.getConnection();
            String query = "SELECT * FROM teachers;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                teacher = new BeanTeacher();
                teacher.setId(rs.getLong("id"));
                teacher.setName(rs.getString("name"));
                teacher.setSurname(rs.getString("surname"));
                teacher.setLastname(rs.getString("lastname"));
                teacher.setBirthday(rs.getString("birthday"));
                teacher.setCurp(rs.getString("curp"));
                teacher.setEmployee_number(rs.getInt("employee_number"));
                Teachers.add(teacher);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> findAll" + e.getMessage());
        }finally {
            teachers.close(conn,ps,rs);
        }
        return Teachers;
    }

    @Override
    public BeanTeacher findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanTeacher> save(BeanTeacher teacher) {
        try{

            if(validacionCurp(teacher.getCurp())){
                return new Response<BeanTeacher>(400,"No se puede repertir la misma curp",teacher,true);
            }
            if (validacionNumeroEmpleado(teacher.getEmployee_number())){
                    return new Response<BeanTeacher>(400,"No se puede repertir el mismo numero de empleado",teacher,true);
            }


            conn = teachers.getConnection();
            String query = "INSERT INTO teachers (name,surname,lastname,birthday,curp,employee_number)"+"VALUES(?,?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setString(1,teacher.getName());
            ps.setString(2,teacher.getSurname());
            ps.setString(3,teacher.getLastname());
            ps.setString(4,teacher.getBirthday());
            ps.setString(5,teacher.getCurp());
            ps.setInt(6,teacher.getEmployee_number());
            if (ps.executeUpdate() == 1){
                return new Response<>(200,"Registrado correctamente", teacher, false);
            }else{
                return new Response<>(200,"Error al registrar",teacher,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: " + e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        }finally{
            teachers.close(conn,ps,rs);
        }
    }

    private boolean validacionNumeroEmpleado(int employee_number) {
        try{
            conn = teachers.getConnection();
            String query = "SELECT * FROM teachers WHERE employee_number = ?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1, employee_number);
            rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> validacionNumeroEmpleado: "+e.getMessage());
        }finally {
            teachers.close(conn,ps,rs);
        }
        return false;
    }

    private boolean validacionCurp(String curp) {
        try{
            conn = teachers.getConnection();
            String query = "SELECT * FROM teachers WHERE curp = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, curp);
            rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> validacionCurp: "+e.getMessage());
        }finally {
            teachers.close(conn,ps,rs);
        }
        return false;
    }

    @Override
    public Response<BeanTeacher> update(BeanTeacher teacher) {
        try{
            conn = teachers.getConnection();
            String query = "UPDATE teachers set name=?,surname=?,lastname=?,birthday=?,curp=?,employee_number=? WHERE id=?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,teacher.getName());
            ps.setString(2,teacher.getSurname());
            ps.setString(3,teacher.getLastname());
            ps.setString(4,teacher.getBirthday());
            ps.setString(5,teacher.getCurp());
            ps.setInt(6,teacher.getEmployee_number());
            ps.setLong(7,teacher.getId());
            if (ps.executeUpdate() == 1){
                return new Response<>(200,"Actualizado correctamente", teacher, false);
            }else{
                return new Response<>(400,"Error al registrar",teacher,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        }finally {
            teachers.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanTeacher> remove(Long id) {
        return null;
    }
}
