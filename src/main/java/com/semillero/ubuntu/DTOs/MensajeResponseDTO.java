package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Entities.Mensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeResponseDTO {
    private Long id;
    private Date fechaCreacion;
    private boolean gestionado;
    // usuario que envia
    private String telefono;
    private String apellidoYNombre;
    private String email;
    private String texto;
    //private Long id_microemprendimiento

    public MensajeResponseDTO(Mensaje entity){
        this.id=entity.getId();
        this.fechaCreacion=entity.getFechaCreacion();
        this.gestionado= entity.isGestionado();
        this.telefono=entity.getTelefono();
        this.email= entity.getEmail();
        this.texto= entity.getTexto();
        this.apellidoYNombre = entity.getApellido() + ", " + entity.getNombre();
    }


}
