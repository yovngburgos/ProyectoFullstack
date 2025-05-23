package com.example.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Contenido;
import com.example.demo.model.Curso;
import com.example.demo.model.Evaluacion;
import com.example.demo.model.Inscripcion;
import com.example.demo.model.Instructor;
import com.example.demo.repository.ContenidoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EvaluacionRepository;
import com.example.demo.repository.InstructorRepository;

public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    // 1. Crear contenido para un curso del instructor
    public Contenido crearContenido(Long idInstructor, Long idCurso, Contenido contenido) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (!curso.getInstructor().getId_instructor().equals(idInstructor)) {
            throw new RuntimeException("Este curso no pertenece al instructor");
        }

        contenido.setCurso(curso);
        return contenidoRepository.save(contenido);
    }

    // 2. Actualizar contenido
    public Contenido actualizarContenido(Long idInstructor, Long idContenido, Contenido actualizado) {
        Contenido contenido = contenidoRepository.findById(idContenido)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));

        if (!contenido.getCurso().getInstructor().getId_instructor().equals(idInstructor)) {
            throw new RuntimeException("El contenido no pertenece al instructor");
        }

        contenido.setTitulo(actualizado.getTitulo());
        contenido.setTipo_contenido(actualizado.getTipo_contenido());
        contenido.setUrl_archivo(actualizado.getUrl_archivo());
        contenido.setFecha_subida(actualizado.getFecha_subida());

        return contenidoRepository.save(contenido);
    }

    // 3. Obtener todas las evaluaciones de los cursos del instructor
    public List<Evaluacion> gestionarEvaluaciones(Long idInstructor) {
        return evaluacionRepository.findAll().stream()
                .filter(e -> e.getCurso().getInstructor().getId_instructor().equals(idInstructor))
                .toList();
    }

    // 4. Responder consultas (simulado)
    public String responderConsultas(Long idInstructor, String mensaje) {
        Instructor instructor = instructorRepository.findById(idInstructor)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));

        return "Instructor " + instructor.getNombre() + " ha respondido: " + mensaje;
    }

    // 5. Monitorear progreso de alumnos en cursos del instructor
    public Map<String, Object> monitorearProgreso(Long idInstructor) {
        List<Curso> cursos = cursoRepository.findAll().stream()
                .filter(c -> c.getInstructor().getId_instructor().equals(idInstructor))
                .toList();

        int totalAlumnos = cursos.stream()
                .mapToInt(c -> c.getInscripciones() != null ? c.getInscripciones().size() : 0)
                .sum();

        double promedioProgreso = cursos.stream()
                .flatMap(c -> c.getInscripciones() != null ? c.getInscripciones().stream() : Stream.empty())
                .mapToInt(Inscripcion::getProgreso)
                .average()
                .orElse(0.0);

        Map<String, Object> reporte = new LinkedHashMap<>();
        reporte.put("totalCursos", cursos.size());
        reporte.put("totalAlumnos", totalAlumnos);
        reporte.put("promedioProgreso", promedioProgreso);

        return reporte;
    }

    // Extra: obtener, guardar, eliminar instructor
    public Optional<Instructor> obtenerPorId(Long id) {
        return instructorRepository.findById(id);
    }

    public List<Instructor> obtenerTodos() {
        return instructorRepository.findAll();
    }

    public Instructor guardar(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public boolean eliminar(Long id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
