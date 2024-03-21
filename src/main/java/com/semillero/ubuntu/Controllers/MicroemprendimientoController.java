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
    @PostMapping("/admin/create")
    public ResponseEntity<?> crearMicroemprendimiento(@Valid @ModelAttribute MicroemprendimientoRequest microemprendimientoRequest) {
        return ResponseEntity.ok(microemprendimientoService.createMicroemprendimiento(microemprendimientoRequest));
    }
    @PutMapping("/admin/edit/{idMicroemprendimiento}")
    public ResponseEntity<?> editMicroemprendimiento(@Valid @PathVariable Long idMicroemprendimiento, @ModelAttribute MicroemprendimientoRequest microemprendimientoRequest) {
        return ResponseEntity.ok(microemprendimientoService.editMicroemprendimiento(idMicroemprendimiento, microemprendimientoRequest));
    }
    @GetMapping("/findByName")
    public ResponseEntity<?> findByNameMicroemprendimiento(@RequestParam("query") String query) {
        return ResponseEntity.ok(microemprendimientoService.findByNameMicroemprendimiento(query));
    }
    @GetMapping("/findByRubro/{id}")
    public ResponseEntity<?> findByRubro(@PathVariable("id") Long idRubro) {
        return ResponseEntity.ok(microemprendimientoService.findByRubro(idRubro));
    }
    @GetMapping("/findById/{idMicroemprendimiento}")
    public ResponseEntity<?> findById(@PathVariable("idMicroemprendimiento") Long idMicroemprendimiento) {
        return ResponseEntity.ok(microemprendimientoService.findById(idMicroemprendimiento));
    }
    @PutMapping("/admin/hide/{idMicroemprendimiento}")
    public ResponseEntity<?> hideMicroemprendimiento(@Valid @PathVariable Long idMicroemprendimiento) {
        microemprendimientoService.hideMicroemprendimiento(idMicroemprendimiento);
        return ResponseEntity.ok().body("Microemprendimiento ocultado exitosamente");
    }
    @GetMapping("/admin/estadisticasGenerales/{idUsuario}")
    public ResponseEntity<?> estadisticas(@PathVariable("idUsuario") Long idUsuario) {
        return ResponseEntity.ok(microemprendimientoService.estadisticas(idUsuario));
    }
    @GetMapping("/admin/findByUser/{idUsuario}")
    public ResponseEntity<?> findByUser(@PathVariable("idUsuario") Long idUsuario) {
        return ResponseEntity.ok(microemprendimientoService.findByUser(idUsuario));
    }
}
