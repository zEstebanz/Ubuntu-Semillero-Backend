package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Entities.Provincia;
import com.semillero.ubuntu.Entities.Rubro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MicroemprendimientoResponse {
    private Long id;
    private String nombre;
    private Rubro rubro;
    private String subrubro;
    private PaisDTO pais;
    private Provincia provincia;
    private String ciudad;
    private String descripcion;
    private String masInfo;
    private Boolean deleted;
    private Boolean gestionado;
    private List<String> images;
    private LocalDate fechaCreacion;
}
