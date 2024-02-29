package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Enums.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {
    String nombre;
    String apellido;
    String email;
    int telefono;
    Rol rol;
    Boolean isDeleted;
}
