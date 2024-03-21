package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import com.semillero.ubuntu.Services.MicroemprendimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/findByName")
    public ResponseEntity<?> findByNameMicroemprendimiento(@RequestParam("query") String query) {
        return ResponseEntity.ok(microemprendimientoService.findByNameMicroemprendimiento(query));
    }
    @GetMapping("/findByRubro/{id}")
    public ResponseEntity<?> findByRubro(@PathVariable("id") Long idRubro) {
        return ResponseEntity.ok(microemprendimientoService.findByRubro(idRubro));
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long idMicroemprendimiento) {
        return ResponseEntity.ok(microemprendimientoService.findById(idMicroemprendimiento));
    }
    @PutMapping("/hide/{id}")
    public ResponseEntity<?> hideMicroemprendimiento(@Valid @PathVariable Long idMicroemprendimiento) {
        microemprendimientoService.hideMicroemprendimiento(idMicroemprendimiento);
        return ResponseEntity.ok().body("Microemprendimiento ocultado exitosamente");
    }
    @GetMapping("/estadisticas")
    public ResponseEntity<?> estadisticas() {
        return ResponseEntity.ok(microemprendimientoService.estadisticas());
    }
}
