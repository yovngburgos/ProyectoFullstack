package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GestorCursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_gestor;

    private String nombre;
    private String correo;
    private String contraseña;
    private String estado;
    private String departamento;

    public GestorCursos() {}

    public GestorCursos(String nombre, String correo, String contraseña, String estado, String departamento) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
        this.departamento = departamento;
    }

    public Long getId_gestor() {
        return id_gestor;
    }

    public void setId_gestor(Long id_gestor) {
        this.id_gestor = id_gestor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    

}
