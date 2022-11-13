package mx.edu.utez.recuperau2.model.calificacion;

import mx.edu.utez.recuperau2.model.students.BeanStudent;

public class BeanCalificacion {

    Long id;

    String subjects;

    Double qualification;

    BeanStudent id_student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    public BeanStudent getId_student() {
        return id_student;
    }

    public void setId_student(BeanStudent id_student) {
        this.id_student = id_student;
    }
}
