package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.PublicacionDTO;
import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Services.PublicacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionServiceImpl publicacionServiceImpl;

    /**
     Endpoints para las publicaciones (Están sujetos a cambio, ya que hay que asignar los roles)
     **/
    @GetMapping("/")
    public ResponseEntity<?> getAllPublicaciones() {  //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(publicacionServiceImpl.getAll());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/permitidas")
    public ResponseEntity<?> getAllPublisPermitidas() { //Visitante
        try {
            return ResponseEntity.status(HttpStatus.OK).body(publicacionServiceImpl.traerPublisNoOcultas());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO) { //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(publicacionServiceImpl.save(publicacionDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\\\\\"error\\\\\\\":\\\\\\\"Error en crear publicacion.\\\\\\\"}\"");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> actualizarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) { //Administrador
        try {
            publicacionServiceImpl.update(id, publicacion);
            return ResponseEntity.ok("Actualizacion correcta");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\\\\\"error\\\\\\\":\\\\\\\"Error en actualizar publicacion.\\\\\\\"}\"");
        }
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<?> bajarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) { //Administrador
        try {
            publicacionServiceImpl.bajaLogica(id, publicacion);
            return ResponseEntity.ok("Baja correcta");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\\\\\"error\\\\\\\":\\\\\\\"Error en bajar publicacion.\\\\\\\"}\"");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>verPubliVisitante(@PathVariable Long id) {
        //Esta funcion solo debe activarla el visitante en teoria, no el ADMIN para no aumentar las vistas (Especificado en tarjeta 17)
        try {
            return ResponseEntity.status(HttpStatus.OK).body(publicacionServiceImpl.verPubliVisitante(id));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
