package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Curso;
import com.example.demo.model.GestorCursos;
import com.example.demo.model.Instructor;
import com.example.demo.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private GestorCursosService gestorCursosService;

    // 1. Crear nuevo curso
    public Curso crearCurso(Long idInstructor, Long idGestor, Curso curso) {
        Instructor instructor = instructorService.obtenerPorId(idInstructor)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        GestorCursos gestor = gestorCursosService.obtenerPorId(idGestor)
                .orElseThrow(() -> new RuntimeException("Gestor de cursos no encontrado"));

        curso.setInstructor(instructor);
        curso.setGestor(gestor);
        return cursoRepository.save(curso);
    }

    // 2. Actualizar curso existente
    public Curso actualizarCurso(Long idCurso, Curso datosActualizados) {
        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setNombre_curso(datosActualizados.getNombre_curso());
            curso.setDescripcion(datosActualizados.getDescripcion());
            curso.setEstado(datosActualizados.getEstado());
            curso.setFecha_creacion(datosActualizados.getFecha_creacion());
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso no encontrado");
        }
    }

    // 3. Eliminar curso
    public boolean eliminarCurso(Long idCurso) {
        if (cursoRepository.existsById(idCurso)) {
            cursoRepository.deleteById(idCurso);
            return true;
        }
        return false;
    }

    // 4. Obtener curso por ID
    public Optional<Curso> obtenerCursoPorId(Long idCurso) {
        return cursoRepository.findById(idCurso);
    }

    // 5. Listar todos los cursos
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    // 6. Activar curso (cambia estado a "Activo")
    public Curso activarCurso(Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setEstado("Activo");
        return cursoRepository.save(curso);
    }

}
