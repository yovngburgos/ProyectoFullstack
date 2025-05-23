package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumno;
import com.example.demo.model.Curso;
import com.example.demo.model.Inscripcion;
import com.example.demo.repository.InscripcionRepository;

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CursoService cursoService;

    // 1. Crear inscripción
    public Inscripcion crearInscripcion(Long idAlumno, Long idCurso, Inscripcion inscripcion) {
        Alumno alumno = alumnoService.obtenerPorId(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Curso curso = cursoService.obtenerCursoPorId(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        inscripcion.setAlumno(alumno);
        inscripcion.setCurso(curso);
        inscripcion.setEstado("Inscrito");
        return inscripcionRepository.save(inscripcion);
    }

    // 2. Actualizar progreso de la inscripción
    public Inscripcion actualizarProgreso(Long idInscripcion, int nuevoProgreso) {
        Inscripcion inscripcion = inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        inscripcion.setProgreso(nuevoProgreso);
        return inscripcionRepository.save(inscripcion);
    }

    // 3. Cancelar inscripción
    public Inscripcion cancelarInscripcion(Long idInscripcion) {
        Inscripcion inscripcion = inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        inscripcion.setEstado("Cancelado");
        return inscripcionRepository.save(inscripcion);
    }

    // 4. Obtener inscripción por ID
    public Optional<Inscripcion> obtenerInscripcionPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    // 5. Listar inscripciones por alumno
    public List<Inscripcion> listarInscripcionesPorUsuario(Long idAlumno) {
        return inscripcionRepository.findAll().stream()
                .filter(i -> i.getAlumno().getId_alumno().equals(idAlumno))
                .toList();
    }

}
