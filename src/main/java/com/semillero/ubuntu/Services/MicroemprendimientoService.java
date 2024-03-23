package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import com.semillero.ubuntu.DTOs.AdminRequest;
import org.springframework.http.ResponseEntity;

public interface MicroemprendimientoService {
    ResponseEntity<?> createMicroemprendimiento(MicroemprendimientoRequest microemprendimientoRequest);
    ResponseEntity<?> editMicroemprendimiento(Long idMicroemprendimiento, MicroemprendimientoRequest microemprendimientoRequest);
    ResponseEntity<?> findByNameMicroemprendimiento(String query);
    ResponseEntity<?> findByRubro(Long idRubro);
    ResponseEntity<?> findById(Long idMicroemprendimiento);
    void hideMicroemprendimiento(Long idMicroemprendimiento);
    ResponseEntity<?> estadisticas(AdminRequest adminRequest);
    ResponseEntity<?> findByUser(AdminRequest adminRequest);
}