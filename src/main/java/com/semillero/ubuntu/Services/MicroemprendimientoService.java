package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import org.springframework.http.ResponseEntity;

public interface MicroemprendimientoService {
    ResponseEntity<?> createMicroemprendimiento(MicroemprendimientoRequest microemprendimientoRequest);
    ResponseEntity<?> editMicroemprendimiento(Long id, MicroemprendimientoRequest microemprendimientoRequest);
}