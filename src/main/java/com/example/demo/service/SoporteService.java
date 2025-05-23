package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Incidencia;
import com.example.demo.model.Soporte;
import com.example.demo.repository.IncidenciaRepository;
import com.example.demo.repository.SoporteRepository;

public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    // 1. Obtener todas las incidencias asignadas a un técnico
    public List<Incidencia> gestionarIncidencias(Long idSoporte) {
        return incidenciaRepository.findAll().stream()
                .filter(i -> i.getSoporte() != null && i.getSoporte().getId_soporte().equals(idSoporte))
                .toList();
    }

    // 2. Actualizar estado de una incidencia (desde el rol soporte)
    public Incidencia actualizarEstadoIncidencias(Long idIncidencia, String nuevoEstado) {
        Incidencia incidencia = incidenciaRepository.findById(idIncidencia)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
        incidencia.setEstado(nuevoEstado);
        return incidenciaRepository.save(incidencia);
    }

    // 3. Responder consultas (simulado)
    public String responderConsultas(Long idSoporte, String mensaje) {
        Soporte soporte = soporteRepository.findById(idSoporte)
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        return "Soporte " + soporte.getNombre() + " ha respondido: " + mensaje;
    }

    // 4. Asignar incidencia a soporte
    public Incidencia asignarIncidencias(Long idIncidencia, Long idSoporte) {
        Incidencia incidencia = incidenciaRepository.findById(idIncidencia)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        Soporte soporte = soporteRepository.findById(idSoporte)
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));

        incidencia.setSoporte(soporte);
        return incidenciaRepository.save(incidencia);
    }

    // Extras
    public Optional<Soporte> obtenerPorId(Long id) {
        return soporteRepository.findById(id);
    }

    public List<Soporte> obtenerTodos() {
        return soporteRepository.findAll();
    }

    public Soporte guardar(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

    public boolean eliminar(Long id) {
        if (soporteRepository.existsById(id)) {
            soporteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
