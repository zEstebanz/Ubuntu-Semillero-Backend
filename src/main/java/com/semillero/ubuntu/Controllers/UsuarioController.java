package com.semillero.ubuntu.Controllers;


import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController{
    @Autowired
    private UsuarioService usuarioService;

    /*Post Mapping para crear el usuario*/
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error en creacion de usuario.\\\"}");
        }
    }
}
