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

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{}")
        }
    }
}
