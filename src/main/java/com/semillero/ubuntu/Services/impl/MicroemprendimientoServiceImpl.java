package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import com.semillero.ubuntu.DTOs.MicroemprendimientoResponse;
import com.semillero.ubuntu.Entities.*;
import com.semillero.ubuntu.Exceptions.MicroemprendimientoException;
import com.semillero.ubuntu.Repositories.*;
import com.semillero.ubuntu.Services.MicroemprendimientoService;
import com.semillero.ubuntu.Services.UtilsMicroemprendimiento;
import com.semillero.ubuntu.Utils.Mapper;
import com.semillero.ubuntu.Utils.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MicroemprendimientoServiceImpl implements MicroemprendimientoService {

    private final MicroemprendimientoRepository microemprendimientoRepository;

    private final UtilsMicroemprendimiento utilsMicroemprendimiento;
    @Override
    @Transactional
    public ResponseEntity<?> createMicroemprendimiento(MicroemprendimientoRequest microemprendimientoRequest) {
        Usuario usuario = utilsMicroemprendimiento.findUsuario(microemprendimientoRequest.getIdUsuario());
        utilsMicroemprendimiento.validationImage(microemprendimientoRequest);

        Microemprendimiento newMicroemprendimiento = new Microemprendimiento();
        newMicroemprendimiento.setNombre(microemprendimientoRequest.getNombre());

        Rubro rubro = utilsMicroemprendimiento.findRubro(microemprendimientoRequest.getIdRubro());
        newMicroemprendimiento.setRubro(rubro);

        newMicroemprendimiento.setSubrubro(microemprendimientoRequest.getSubrubro());

        Pais pais = utilsMicroemprendimiento.findPais(microemprendimientoRequest.getIdPais());
        newMicroemprendimiento.setPais(pais);

        Provincia provincia = utilsMicroemprendimiento.findProvincia(microemprendimientoRequest.getIdProvincia());
        newMicroemprendimiento.setProvincia(provincia);

        newMicroemprendimiento.setCiudad(microemprendimientoRequest.getCiudad());
        newMicroemprendimiento.setDescripcion(microemprendimientoRequest.getDescripcion());
        newMicroemprendimiento.setMasInfo(microemprendimientoRequest.getMasInfo());
        newMicroemprendimiento.setDeleted(false);
        newMicroemprendimiento.setGestionado(false);
        newMicroemprendimiento.setFechaCreacion((LocalDate.now()));

        List<Map> upload = utilsMicroemprendimiento.saveInCloud(microemprendimientoRequest.getImages());

        List<Image> images = utilsMicroemprendimiento.saveInBd(upload);

        newMicroemprendimiento.setImages(images);

        newMicroemprendimiento.setUsuario(usuario);
        microemprendimientoRepository.save(newMicroemprendimiento);
        MicroemprendimientoResponse microemprendimientoResponse =
                Mapper.microemprendimientoToResponse(newMicroemprendimiento, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(microemprendimientoResponse);
    }
    @Override
    @Transactional
    public ResponseEntity<?> editMicroemprendimiento(Long idMicroemprendimiento, MicroemprendimientoRequest microemprendimientoRequest) {
        Usuario usuario = utilsMicroemprendimiento.findUsuario(microemprendimientoRequest.getIdUsuario());
        utilsMicroemprendimiento.validationImage(microemprendimientoRequest);

        Microemprendimiento editMicroemprendimiento = microemprendimientoRepository.findById(idMicroemprendimiento)
                        .orElseThrow( () -> new EntityNotFoundException("Microemprendimiento not found with id: " + idMicroemprendimiento));
        if (!Objects.equals(usuario.getId(), editMicroemprendimiento.getUsuario().getId())){
            throw new MicroemprendimientoException("No puede editar esta publicacion");
        }
        editMicroemprendimiento.setNombre(microemprendimientoRequest.getNombre());

        Rubro rubro = utilsMicroemprendimiento.findRubro(microemprendimientoRequest.getIdRubro());
        editMicroemprendimiento.setRubro(rubro);

        editMicroemprendimiento.setSubrubro(microemprendimientoRequest.getSubrubro());

        Pais pais = utilsMicroemprendimiento.findPais(microemprendimientoRequest.getIdPais());
        editMicroemprendimiento.setPais(pais);

        Provincia provincia = utilsMicroemprendimiento.findProvincia(microemprendimientoRequest.getIdProvincia());
        editMicroemprendimiento.setProvincia(provincia);

        editMicroemprendimiento.setCiudad(microemprendimientoRequest.getCiudad());
        editMicroemprendimiento.setDescripcion(microemprendimientoRequest.getDescripcion());
        editMicroemprendimiento.setMasInfo(microemprendimientoRequest.getMasInfo());
        editMicroemprendimiento.setFechaCreacion((LocalDate.now()));

        utilsMicroemprendimiento.deleteInCloud(editMicroemprendimiento.getImages());
        utilsMicroemprendimiento.deleteInBd(editMicroemprendimiento.getImages());

        List<Map> upload = utilsMicroemprendimiento.saveInCloud(microemprendimientoRequest.getImages());

        List<Image> images = utilsMicroemprendimiento.saveInBd(upload);

        editMicroemprendimiento.setImages(images);

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
        return ResponseEntity.status(HttpStatus.OK)
                        .body(MapperUtil.toDTOList(microemprendimientoList, MicroemprendimientoResponse.class));
    }
    @Transactional
    @Override
    public ResponseEntity<?> findByRubro(Long idRubro) {
        List<Microemprendimiento> microemprendimientoList = microemprendimientoRepository.findByRubro(idRubro);
        if(microemprendimientoList.isEmpty()){
            throw new EntityNotFoundException("No se encontraron microemprendimientos con este rubro");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(MapperUtil.toDTOList(microemprendimientoList, MicroemprendimientoResponse.class));
    }
    @Transactional
    @Override
    public ResponseEntity<?> findById(Long idMicroemprendimiento) {
        Microemprendimiento microemprendimiento = microemprendimientoRepository.findByIdAndDeletedFalse(idMicroemprendimiento)
                .orElseThrow(() -> new EntityNotFoundException("Microemprendimiento not found with id: "+ idMicroemprendimiento));
        return ResponseEntity.status(HttpStatus.OK)
                .body(MapperUtil.mapToDto(microemprendimiento, MicroemprendimientoResponse.class));
    }
    @Transactional
    @Override
    public void hideMicroemprendimiento(Long idMicroemprendimiento) {
        Microemprendimiento microemprendimiento = microemprendimientoRepository.findById(idMicroemprendimiento)
                .orElseThrow( () -> new EntityNotFoundException("Microemprendimiento not found with id: " + idMicroemprendimiento));
        microemprendimiento.setDeleted(true);
        microemprendimientoRepository.save(microemprendimiento);
    }
    @Override
    public ResponseEntity<?> estadisticas(Long idUsuario) {
        List<Object[]> resultados = microemprendimientoRepository.estadisticas(idUsuario);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.objectToEstadisticaDTO(resultados));
    }
    @Override
    public ResponseEntity<?> findByUser(Long idUsuario) {
        List<Microemprendimiento> microemprendimientoList =
                microemprendimientoRepository.findAllByUsuarioIdAndDeletedFalse(idUsuario);
        if(microemprendimientoList.isEmpty()){
            throw new EntityNotFoundException("No se encontraron microemprendimientos asociados a este usuario " + idUsuario);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(MapperUtil.toDTOList(microemprendimientoList, MicroemprendimientoResponse.class));
    }
}