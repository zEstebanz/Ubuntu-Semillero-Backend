package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.Entities.*;
import com.semillero.ubuntu.Exceptions.ImageException;
import com.semillero.ubuntu.Repositories.*;
import com.semillero.ubuntu.Services.UtilsMicroemprendimiento;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UtilsMicroemprendimientoImpl implements UtilsMicroemprendimiento {

    private final UsuarioRepository usuarioRepository;

    private final RubroRepository rubroRepository;

    private final PaisRepository paisRepository;

    private final ProvinciaRepository provinciaRepository;

    private final CloudinaryImageServiceImpl cloudinaryImageService;

    private final ImageRepository imageRepository;
    private final long maxSize = 3 * 1024 * 1024;

    @Override
    public Usuario findUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow( () -> new EntityNotFoundException("Usuario no encontrado con el email: "
                        + email));
    }
    @Override
    public void validationImage(List<MultipartFile> listImages) {
        for (MultipartFile images : listImages){
            if (images.getSize() > maxSize){
                throw new ImageException("El archivo no puede pesar más de 3mb");
            }
        }
        if(listImages.size() == 0 || listImages.size() > 3) {
            throw new ImageException("Debes agregar 1 imágen como mínimo y 3 como máximo");
        }
    }
    @Override
    public Rubro findRubro(Long idRubro) {
        return rubroRepository.findById(idRubro)
                .orElseThrow( () -> new EntityNotFoundException("Rubro no encontrado con el id: "
                        + idRubro));
    }
    @Override
    public Pais findPais(Long idPais) {
        return paisRepository.findById(idPais)
                .orElseThrow( () -> new EntityNotFoundException("Pais no encontrado con el id: "
                        + idPais));
    }
    @Override
    public Provincia findProvincia(Long idProvincia) {
        return provinciaRepository.findById(idProvincia)
                .orElseThrow( () -> new EntityNotFoundException("Provincia no encontrada con el id: "
                        + idProvincia));
    }
    @Override
    public List<Map> saveInCloud(List<MultipartFile> imagesFiles) {
        return imagesFiles
               .stream()
               .map(cloudinaryImageService::upload)
               .collect(Collectors.toList());
    }
    @Override
    public List<Image> saveInBd(List<Map> upload) {
        List<Image> images = upload.stream()
                .map(Image::createImage)
                .collect(Collectors.toList());

        images.forEach(imageRepository::save);
        return images;
    }
    @Override
    public void deleteInCloud(List<Image> images) {
        images.forEach
                (image -> cloudinaryImageService.delete(String.valueOf(image.getId())));
    }
    @Override
    public void deleteInBd(List<Image> images) {
        images.forEach
                (image -> imageRepository.deleteById(image.getId()));
    }
}
