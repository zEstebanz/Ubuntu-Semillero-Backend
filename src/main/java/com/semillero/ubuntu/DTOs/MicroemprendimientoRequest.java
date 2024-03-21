package com.semillero.ubuntu.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class MicroemprendimientoRequest {
    @Valid

    @NotBlank(message = "El nombre es obligatorio")
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "Debe elegir un rubro")
    private Long idRubro;

    private String subrubro;

    @NotNull(message = "Debe elegir un pais")
    private Long idPais;

    @NotNull(message = "Debe elegir una provincia")
    private Long idProvincia;

    @NotBlank(message = "Debe ingresar una ciudad")
    @NotEmpty(message = "El campo ciudad no puede estar vacío")
    private String ciudad;

    @Size(max = 300, message = "El texto debe tener un máximo de 300 carácteres")
    @NotEmpty(message = "El campo descripcion no puede estar vacío")
    @NotBlank(message = "El campo descripcion es obligatorio")
    private String descripcion;

    @Size(max = 300, message = "El texto debe tener un máximo de 300 carácteres")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotBlank(message = "Este campo es obligatorio")
    private String masInfo;

    private List<MultipartFile> images;
    //private List<Mensaje> mensajes;
}
