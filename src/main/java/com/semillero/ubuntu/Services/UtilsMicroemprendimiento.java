package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import com.semillero.ubuntu.Entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UtilsMicroemprendimiento {
    Usuario findUsuario(String email);
    void validationImage(List<MultipartFile> listImages);
    Rubro findRubro(Long idRubro);
    Pais findPais(Long idPais);
    Provincia findProvincia(Long idProvincia);
    List<Map> saveInCloud(List<MultipartFile> imagesFiles);
    List<Image> saveInBd(List<Map> upload);
    void deleteInCloud(List<Image> images);
    void deleteInBd(List<Image> images);
}
