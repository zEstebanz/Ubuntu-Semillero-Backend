package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.AddImageToPublication;
import com.semillero.ubuntu.DTOs.PublicacionDTO;
import com.semillero.ubuntu.DTOs.PublicationResponse;
import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Services.impl.PublicacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionServiceImpl publicacionServiceImpl;

    /**
     Endpoints para las publicaciones (Están sujetos a cambio, ya que hay que asignar los roles)
     <p>
     Lo de @CrossOrigin investigarlo de meterlo en las variables de entorno
     **/
    @GetMapping("/admin/getAll")
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

    @PostMapping("/admin/create")
    public ResponseEntity<PublicationResponse> crearPublicacion(@ModelAttribute PublicacionDTO publicacionDTO) { //Administrador

        return ResponseEntity.ok(publicacionServiceImpl.crearPublicacion(publicacionDTO));
    }

    //En los 3 métodos siguientes hay que ver si envía un response entity en un try catch o si solamente dejarlo asi
    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@PathVariable Long id, @RequestBody PublicacionDTO publicacionDTO) { //Administrador

        return ResponseEntity.ok(publicacionServiceImpl.editarPublicacion(id,publicacionDTO));
    }

    @PutMapping("/admin/baja/{id}")
    public ResponseEntity<?> bajarPublicacion(@PathVariable Long id) { //Administrador
        publicacionServiceImpl.bajaLogica(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>verPubliVisitante(@PathVariable Long id) {
        //Esta funcion solo debe activarla el visitante en teoria, no el ADMIN para no aumentar las vistas (Especificado en tarjeta 17)
        publicacionServiceImpl.verPubliVisitante(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ultimasTres")
    public ResponseEntity<?> traerUltimasTres() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(publicacionServiceImpl.traerUltimasTres());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PutMapping("/add-image")
    public ResponseEntity<PublicationResponse> addImageToPublication(@RequestBody AddImageToPublication addImage){

        return ResponseEntity.ok(publicacionServiceImpl.addImage(addImage));
    }
}
