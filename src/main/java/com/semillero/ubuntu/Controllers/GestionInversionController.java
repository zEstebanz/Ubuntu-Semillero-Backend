package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.GestionInversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;
import com.semillero.ubuntu.Services.impl.GestionInversionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("GestionInversion")
public class GestionInversionController {
    @Autowired
    private GestionInversionServiceImpl gestionInversionServiceImpl;

    @GetMapping("/admin/getAll")
    public ResponseEntity<?> getAllgestionesInversion() {   //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.getAll());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/calcularInversion") //El de ambas entidades se encuentra en el DTO
    public ResponseEntity<?> calcularInversion(@RequestBody RecibirInversionDTO recibirInversionDTO) {  //Visitante
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.calcularInversion(recibirInversionDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/admin/crearInversion")
    public ResponseEntity<?> crearGestion(@RequestBody GestionInversionDTO gestionInversionDTO) {   //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.crearGestion(gestionInversionDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/admin/editarGestion/{id}")
    public ResponseEntity<?> editarGestion(@RequestParam Long id, @RequestBody GestionInversionDTO gestionInversionDTO) {   //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.editarGestion(id, gestionInversionDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
