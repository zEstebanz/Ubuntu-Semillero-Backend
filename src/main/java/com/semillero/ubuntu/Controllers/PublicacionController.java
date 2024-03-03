package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Services.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    @PostMapping("/create")
    public ResponseEntity<?> crearPublicacion(@RequestBody Publicacion publicacion) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\\\\\"error\\\\\\\":\\\\\\\"Error en crear publicacion.\\\\\\\"}\"");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        try {
            publicacionService.update(id, publicacion);
            return ResponseEntity.ok("Actualizacion correcta");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\\\\\"error\\\\\\\":\\\\\\\"Error en actualizar publicacion.\\\\\\\"}\"");
        }
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<?> bajarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        try {
            publicacionService.bajaLogica(id, publicacion);
            return ResponseEntity.ok("Baja correcta");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\\\\\"error\\\\\\\":\\\\\\\"Error en bajar publicacion.\\\\\\\"}\"");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>verPublicacion(@PathVariable Long id) { //Esta funcion solo debe activarla el visitante en teoria
        try {
            publicacionService.verPublicacion(id);
            return ResponseEntity.ok("Vista correcta");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
