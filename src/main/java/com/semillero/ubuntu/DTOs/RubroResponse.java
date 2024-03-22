package com.semillero.ubuntu.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RubroResponse {
    private Long id;
    private String nombre;
    private Long cantidadMicroemprendimientos;
}
