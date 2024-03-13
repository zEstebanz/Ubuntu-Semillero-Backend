package com.semillero.ubuntu.Controllers;


import com.semillero.ubuntu.DTOs.UsuarioDTO;
import com.semillero.ubuntu.Services.impl.UsuarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController{
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

   @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceImpl.crearUsuario(usuarioDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error en creacion de usuario.\\\"}");
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceImpl.editarUsuario(id ,usuarioDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error en la edicion de usuario.\\\"}");
        }
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<?> bajaLogica(@PathVariable Long id) {
           usuarioServiceImpl.bajaLogica(id);
           return ResponseEntity.ok().build();
    }

}
