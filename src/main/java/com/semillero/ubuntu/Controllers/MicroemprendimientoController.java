package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import com.semillero.ubuntu.Services.MicroemprendimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/microemprendimientos")
@RequiredArgsConstructor
public class MicroemprendimientoController {

    private final MicroemprendimientoService microemprendimientoService;
    @PostMapping("/create")
    public ResponseEntity<?> crearMicroemprendimiento(@Valid @ModelAttribute MicroemprendimientoRequest microemprendimientoRequest) {
        return ResponseEntity.ok(microemprendimientoService.createMicroemprendimiento(microemprendimientoRequest));
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editMicroemprendimiento(@Valid @PathVariable Long id, @ModelAttribute MicroemprendimientoRequest microemprendimientoRequest) {
        return ResponseEntity.ok(microemprendimientoService.editMicroemprendimiento(id, microemprendimientoRequest));
    }
}
