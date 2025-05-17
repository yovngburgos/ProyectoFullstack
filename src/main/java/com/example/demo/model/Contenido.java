package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contenido;

    private String titulo;
    private String tipo_contenido;
    private String url_archivo;
    private String fecha_subida;
    private Long id_curso;

    public Contenido() {}

    public Contenido(String titulo, String tipo_contenido, String url_archivo, String fecha_subida, Long id_curso) {
        this.titulo = titulo;
        this.tipo_contenido = tipo_contenido;
        this.url_archivo = url_archivo;
        this.fecha_subida = fecha_subida;
        this.id_curso = id_curso;
    }

    
    


    /**
     * @return Long return the id_contenido
     */
    public Long getId_contenido() {
        return id_contenido;
    }

    /**
     * @param id_contenido the id_contenido to set
     */
    public void setId_contenido(Long id_contenido) {
        this.id_contenido = id_contenido;
    }

    /**
     * @return String return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return String return the tipo_contenido
     */
    public String getTipo_contenido() {
        return tipo_contenido;
    }

    /**
     * @param tipo_contenido the tipo_contenido to set
     */
    public void setTipo_contenido(String tipo_contenido) {
        this.tipo_contenido = tipo_contenido;
    }

    /**
     * @return String return the url_archivo
     */
    public String getUrl_archivo() {
        return url_archivo;
    }

    /**
     * @param url_archivo the url_archivo to set
     */
    public void setUrl_archivo(String url_archivo) {
        this.url_archivo = url_archivo;
    }

    /**
     * @return String return the fecha_subida
     */
    public String getFecha_subida() {
        return fecha_subida;
    }

    /**
     * @param fecha_subida the fecha_subida to set
     */
    public void setFecha_subida(String fecha_subida) {
        this.fecha_subida = fecha_subida;
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

}
