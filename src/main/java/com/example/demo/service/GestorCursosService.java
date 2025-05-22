package com.example.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Curso;
import com.example.demo.model.GestorCursos;
import com.example.demo.model.Instructor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.GestorCursosRepository;
import com.example.demo.repository.InstructorRepository;

@Service
public class GestorCursosService {

    @Autowired
    private GestorCursosRepository gestorCursosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    // 1. Gestionar cursos asignados al gestor
    public List<Curso> gestionarCursos(Long idGestor) {
        return cursoRepository.findAll().stream()
                .filter(c -> c.getGestor().getId_gestor().equals(idGestor))
                .toList();
    }

    // 2. Generar reportes por cursos (simulado: cantidad de cursos e inscritos por gestor)
    public Map<String, Object> generarReportes(Long idGestor) {
        List<Curso> cursos = gestionarCursos(idGestor);
        int totalCursos = cursos.size();
        int totalInscripciones = cursos.stream()
                .mapToInt(c -> c.getInscripciones() != null ? c.getInscripciones().size() : 0)
                .sum();

        Map<String, Object> reporte = new LinkedHashMap<>();
        reporte.put("totalCursos", totalCursos);
        reporte.put("totalInscripciones", totalInscripciones);
        reporte.put("detalleCursos", cursos);
        return reporte;
    }

    // 3. Listar instructores activos
    public List<Instructor> gestionarInstructores() {
        return instructorRepository.findAll().stream()
                .filter(i -> i.getEstado().equalsIgnoreCase("Activo"))
                .toList();
    }

    // 4. Evaluar contenido (simulado)
    public String evaluarContenido(Long idCurso, String evaluacionGestor) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        // Aquí se podría establecer un campo "evaluacion" en Curso si se desea
        return "El curso '" + curso.getNombre_curso() + "' ha sido evaluado: " + evaluacionGestor;
    }

    // Extra: Obtener gestor por ID
    public Optional<GestorCursos> obtenerPorId(Long id) {
        return gestorCursosRepository.findById(id);
    }

    public List<GestorCursos> obtenerTodos() {
        return gestorCursosRepository.findAll();
    }

    public GestorCursos guardar(GestorCursos g) {
        return gestorCursosRepository.save(g);
    }

    public boolean eliminar(Long id) {
        if (gestorCursosRepository.existsById(id)) {
            gestorCursosRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
