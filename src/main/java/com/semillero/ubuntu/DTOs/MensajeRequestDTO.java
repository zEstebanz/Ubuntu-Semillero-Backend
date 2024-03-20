package com.semillero.ubuntu.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRequestDTO {

    @Valid

    @NotNull(message = "This field can´t be null")
    @NotBlank(message = "This field can´t be blank")
    @NotEmpty(message = "This field can´t be empty")
    private String apellidoYNombre;

    @NotNull(message = "This field can´t be null")
    @NotBlank(message = "This field can´t be blank")
    @NotEmpty(message = "This field can´t be empty")
    @Size(min = 6, max = 20, message = "This field must have a length between 6 and 20")
    private String telefono;

    @NotNull(message = "This field can´t be null")
    @Email(message = "This field must have a @")
    private String email;

    @NotNull(message = "This field can´t be null")
    @NotBlank(message = "This field can´t be blank")
    @NotEmpty(message = "This field can´t be empty")
    @Size(min = 4,max = 300)
    private String texto;
    //private Long id_microemprendimiento;


}
