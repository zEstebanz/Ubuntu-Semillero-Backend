package com.semillero.ubuntu.Controllers;


import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "usuarios")
public class UsuarioController{
    @Autowired
    private UsuarioService usuarioService;

   @PostMapping("/create")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error en creacion de usuario.\\\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id,@RequestBody Usuario usuario) {
       try {
           usuarioService.update(id, usuario);
           return ResponseEntity.ok("Actualizacion correcta");
       } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error en actualizar usuario.\\\"}");
       }
    }
}
