package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    private String nombre_curso;
    private String descripcion;
    private String estado;
    private String fecha_creacion;
    private Long id_instructor;
    private Long id_gestor;

    public Curso() {}

    public Curso(String nombre_curso, String descripcion, String estado, String fecha_creacion, Long id_instructor, Long id_gestor) {
        this.nombre_curso = nombre_curso;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.id_instructor = id_instructor;
        this.id_gestor = id_gestor;
    }

    



    /**
     * @return Long return the id_curso
     */
    public Long getId_curso() {
        return id_curso;
    }

    /**
     * @param id_curso the id_curso to set
     */
    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }

    /**
     * @return String return the nombre_curso
     */
    public String getNombre_curso() {
        return nombre_curso;
    }

    /**
     * @param nombre_curso the nombre_curso to set
     */
    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    /**
     * @return String return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return String return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return String return the fecha_creacion
     */
    public String getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * @return Long return the id_instructor
     */
    public Long getId_instructor() {
        return id_instructor;
    }

    /**
     * @param id_instructor the id_instructor to set
     */
    public void setId_instructor(Long id_instructor) {
        this.id_instructor = id_instructor;
    }

    /**
     * @return Long return the id_gestor
     */
    public Long getId_gestor() {
        return id_gestor;
    }

    /**
     * @param id_gestor the id_gestor to set
     */
    public void setId_gestor(Long id_gestor) {
        this.id_gestor = id_gestor;
    }

}
