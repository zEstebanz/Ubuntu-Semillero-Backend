package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Enums.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {

    String nombre;
    String apellido;
    String email;
    Rol rol;
    Boolean isDeleted; //Para crear usuario este valor siempre debe estar en 0
}
