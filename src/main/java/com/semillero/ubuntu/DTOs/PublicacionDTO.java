package com.semillero.ubuntu.DTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PublicacionDTO {
    String titulo;
    String descripcion;
    Boolean isDeleted;
    Long idUsuario;
    List<MultipartFile> images;
}
