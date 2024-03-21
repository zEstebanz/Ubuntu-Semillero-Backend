package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import com.semillero.ubuntu.DTOs.MicroemprendimientoResponse;
import com.semillero.ubuntu.Entities.*;
import com.semillero.ubuntu.Exceptions.ImageException;
import com.semillero.ubuntu.Exceptions.MicroemprendimientoException;
import com.semillero.ubuntu.Repositories.*;
import com.semillero.ubuntu.Services.MicroemprendimientoService;
import com.semillero.ubuntu.Utils.Mapper;
import com.semillero.ubuntu.Utils.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MicroemprendimientoServiceImpl implements MicroemprendimientoService {

    private final MicroemprendimientoRepository microemprendimientoRepository;

    private final CloudinaryImageServiceImpl cloudinaryImageService;

    private final ImageRepository imageRepository;

    private final RubroRepository rubroRepository;

    private final PaisRepository paisRepository;

    private final ProvinciaRepository provinciaRepository;
    private final long maxSize = 3 * 1024 * 1024;
    @Override
    @Transactional
    public ResponseEntity<?> createMicroemprendimiento(MicroemprendimientoRequest microemprendimientoRequest) {
        for (MultipartFile img : microemprendimientoRequest.getImages()){
            if (img.getSize() > maxSize){
                throw new ImageException("El archivo no puede pesar más de 3mb");
            }
        }
        if(microemprendimientoRequest.getImages().size() == 0 || microemprendimientoRequest.getImages().size() > 3) {
            throw new ImageException("Debes agregar 1 imágen como mínimo y 3 como máximo");
        }
        Microemprendimiento newMicroemprendimiento = new Microemprendimiento();
        newMicroemprendimiento.setNombre(microemprendimientoRequest.getNombre());

        Rubro rubro = rubroRepository.findById(microemprendimientoRequest.getIdRubro())
                .orElseThrow( () -> new EntityNotFoundException("Rubro no encontrado con el id: "
                        + microemprendimientoRequest.getIdRubro()));
        newMicroemprendimiento.setRubro(rubro);

        newMicroemprendimiento.setSubrubro(microemprendimientoRequest.getSubrubro());

        Pais pais = paisRepository.findById(microemprendimientoRequest.getIdPais())
                .orElseThrow( () -> new EntityNotFoundException("Pais no encontrado con el id: "
                        + microemprendimientoRequest.getIdPais()));
        newMicroemprendimiento.setPais(pais);

        Provincia provincia = provinciaRepository.findById(microemprendimientoRequest.getIdProvincia())
                .orElseThrow( () -> new EntityNotFoundException("Provincia no encontrada con el id: "
                        + microemprendimientoRequest.getIdProvincia()));
        newMicroemprendimiento.setProvincia(provincia);

        newMicroemprendimiento.setCiudad(microemprendimientoRequest.getCiudad());
        newMicroemprendimiento.setDescripcion(microemprendimientoRequest.getDescripcion());
        newMicroemprendimiento.setMasInfo(microemprendimientoRequest.getMasInfo());
        newMicroemprendimiento.setDeleted(false);
        newMicroemprendimiento.setGestionado(false);

        List<Map> upload = microemprendimientoRequest.getImages()
                .stream()
                .map(cloudinaryImageService::upload)
                .toList();

        List<Image> images = upload.stream().map(Image::createImage).toList();

        newMicroemprendimiento.setImages(images);
        images.forEach(imageRepository::save);

        microemprendimientoRepository.save(newMicroemprendimiento);
        MicroemprendimientoResponse microemprendimientoResponse =
                Mapper.microemprendimientoToResponse(newMicroemprendimiento, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(microemprendimientoResponse);
    }
    @Override
    @Transactional
    public ResponseEntity<?> editMicroemprendimiento(Long id, MicroemprendimientoRequest microemprendimientoRequest) {
        for (MultipartFile img : microemprendimientoRequest.getImages()){
            if (img.getSize() > maxSize){
                throw new ImageException("El archivo no puede pesar más de 3mb");
            }
        }
        Microemprendimiento editMicroemprendimiento = microemprendimientoRepository.findById(id)
                        .orElseThrow( () -> new EntityNotFoundException("Microemprendimiento not found with id: " + id));

        if(microemprendimientoRequest.getImages().size() == 0 || microemprendimientoRequest.getImages().size() > 3){
            throw new ImageException("Debes agregar 1 imágen como mínimo y 3 como máximo");
        }
        editMicroemprendimiento.setNombre(microemprendimientoRequest.getNombre());

        Rubro rubro = rubroRepository.findById(microemprendimientoRequest.getIdRubro())
                .orElseThrow( () -> new EntityNotFoundException("Rubro no encontrado con el id: "
                        + microemprendimientoRequest.getIdRubro()));
        editMicroemprendimiento.setRubro(rubro);

        editMicroemprendimiento.setSubrubro(microemprendimientoRequest.getSubrubro());

        Pais pais = paisRepository.findById(microemprendimientoRequest.getIdPais())
                .orElseThrow( () -> new EntityNotFoundException("Pais no encontrado con el id: "
                        + microemprendimientoRequest.getIdPais()));
        editMicroemprendimiento.setPais(pais);

        Provincia provincia = provinciaRepository.findById(microemprendimientoRequest.getIdProvincia())
                .orElseThrow( () -> new EntityNotFoundException("Provincia no encontrada con el id: "
                        + microemprendimientoRequest.getIdProvincia()));
        editMicroemprendimiento.setProvincia(provincia);

        editMicroemprendimiento.setCiudad(microemprendimientoRequest.getCiudad());
        editMicroemprendimiento.setDescripcion(microemprendimientoRequest.getDescripcion());
        editMicroemprendimiento.setMasInfo(microemprendimientoRequest.getMasInfo());

        for (Image image : editMicroemprendimiento.getImages()) {
            Long imageId = image.getId();
            cloudinaryImageService.delete(String.valueOf(imageId));
        }

        editMicroemprendimiento.getImages().forEach
                (image -> imageRepository.deleteById(image.getId()));

        List<Map> upload = microemprendimientoRequest.getImages()
                .stream()
                .map(cloudinaryImageService::upload)
                .collect(Collectors.toList());

        List<Image> images = upload
                .stream()
                .map(Image::createImage)
                .collect(Collectors.toList());

        editMicroemprendimiento.setImages(images);
        images.forEach(imageRepository::save);

        microemprendimientoRepository.save(editMicroemprendimiento);
        MicroemprendimientoResponse microemprendimientoResponse =
                Mapper.microemprendimientoToResponse(editMicroemprendimiento, images);

        return ResponseEntity.status(HttpStatus.OK).body(microemprendimientoResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<?> findByNameMicroemprendimiento(String query) {
        if (query == null || query.isEmpty() || query.isBlank()) {
            throw new MicroemprendimientoException("Escriba un nombre");
        }
        List<Microemprendimiento> microemprendimientoList =
                microemprendimientoRepository.findByNameMicroemprendimiento(query);
        if (microemprendimientoList.isEmpty()) {
            throw new MicroemprendimientoException("No se encontraron microemprendimientos");
        }
        return ResponseEntity.status(HttpStatus.OK).
                body(MapperUtil.toDTOList(microemprendimientoList, MicroemprendimientoResponse.class));
    }
}