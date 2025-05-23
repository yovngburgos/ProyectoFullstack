package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumno;
import com.example.demo.model.Incidencia;
import com.example.demo.model.Soporte;
import com.example.demo.repository.IncidenciaRepository;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private SoporteService soporteService;

    // 1. Crear incidencia
    public Incidencia crearIncidencia(Long idAlumno, Incidencia incidencia) {
        Alumno alumno = alumnoService.obtenerPorId(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        incidencia.setAlumno(alumno);
        incidencia.setEstado("Pendiente");
        return incidenciaRepository.save(incidencia);
    }

    // 2. Actualizar estado de la incidencia
    public Incidencia actualizarEstadoIncidencia(Long idIncidencia, String nuevoEstado) {
        Incidencia incidencia = incidenciaRepository.findById(idIncidencia)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        incidencia.setEstado(nuevoEstado);
        return incidenciaRepository.save(incidencia);
    }

    // 3. Asignar técnico de soporte
    public Incidencia asignarTecnico(Long idIncidencia, Long idSoporte) {
        Incidencia incidencia = incidenciaRepository.findById(idIncidencia)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        Soporte soporte = soporteService.obtenerPorId(idSoporte)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        incidencia.setSoporte(soporte);
        return incidenciaRepository.save(incidencia);
    }

    // 4. Cerrar incidencia
    public Incidencia cerrarIncidencia(Long idIncidencia) {
        Incidencia incidencia = incidenciaRepository.findById(idIncidencia)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        incidencia.setEstado("Cerrada");
        return incidenciaRepository.save(incidencia);
    }

    // 5. Listar incidencias por alumno
    public List<Incidencia> listarIncidenciasPorUsuario(Long idAlumno) {
        return incidenciaRepository.findAll().stream()
                .filter(i -> i.getAlumno().getId_alumno().equals(idAlumno))
                .toList();
    }

}
