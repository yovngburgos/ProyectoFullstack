package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cupon;
import com.example.demo.repository.CuponRepository;

@Service
public class CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    // 1. Crear nuevo cupón
    public Cupon crearCupon(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    // 2. Validar cupón por código
    public boolean validarCupon(String codigo) {
        Optional<Cupon> cuponOpt = cuponRepository.findAll().stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();

        if (cuponOpt.isPresent()) {
            Cupon cupon = cuponOpt.get();
            boolean activo = cupon.getEstado().equalsIgnoreCase("Activo");
            boolean noExpirado = LocalDate.parse(cupon.getFecha_expiracion()).isAfter(LocalDate.now());
            return activo && noExpirado;
        }
        return false;
    }

    // 3. Eliminar cupón por ID
    public boolean eliminarCupon(Long id) {
        if (cuponRepository.existsById(id)) {
            cuponRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 4. Obtener cupón por código
    public Optional<Cupon> obtenerCuponPorCodigo(String codigo) {
        return cuponRepository.findAll().stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    // 5. Listar cupones activos
    public List<Cupon> listarCuponesActivos() {
        LocalDate hoy = LocalDate.now();
        return cuponRepository.findAll().stream()
                .filter(c -> c.getEstado().equalsIgnoreCase("Activo"))
                .filter(c -> LocalDate.parse(c.getFecha_expiracion()).isAfter(hoy))
                .toList();
    }

}
