package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.GestionInversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;
import com.semillero.ubuntu.Services.impl.GestionInversionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gestionInversion")
public class GestionInversionController {
    @Autowired
    private GestionInversionServiceImpl gestionInversionServiceImpl;

    /**
     * Es importante recordar que si el microemprendimiento esta oculto los endpoints del gestionador siguen funcionando,
     * por esta razon el alcance es modelado de tal forma que si se oculta un microemprendimiento,
     * tambien se oculta la funcionalidad del gestionador
    **/


    @PostMapping("/calcularInversion") //El de ambas entidades se encuentra en el DTO
    public ResponseEntity<?> calcularInversion(@RequestBody RecibirInversionDTO recibirInversionDTO) {  //Visitante
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.calcularInversion(recibirInversionDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/admin/create")
    public ResponseEntity<?> crearGestion(@RequestBody GestionInversionDTO gestionInversionDTO) {   //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.crearGestion(gestionInversionDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/admin/edit")
    public ResponseEntity<?> editarGestion(@RequestBody GestionInversionDTO gestionInversionDTO) {   //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.editarGestion(gestionInversionDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/admin/logic/{idMicro}")
    public ResponseEntity<?> logicaGestion(@PathVariable Long idMicro) {    //Administrador
        try {
            gestionInversionServiceImpl.logicaGestion(idMicro);
            return ResponseEntity.status(HttpStatus.OK).body("Logica correcta");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/admin/{idMicro}")
    public ResponseEntity<?> getInversion(@PathVariable Long idMicro) {     //Administrador
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.getInversion(idMicro));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{idMicro}")
    public ResponseEntity<?>getInversionVisitante(@PathVariable Long idMicro) {     //Visitante
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gestionInversionServiceImpl.getInversionVisitante(idMicro));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
