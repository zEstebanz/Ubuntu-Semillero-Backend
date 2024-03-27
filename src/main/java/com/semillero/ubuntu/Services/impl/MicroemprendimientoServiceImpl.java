package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.MicroemprendimientoRequest;
import com.semillero.ubuntu.DTOs.MicroemprendimientoResponse;
import com.semillero.ubuntu.DTOs.AdminRequest;
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
    private LocalDate fecha = LocalDate.now();
    private Integer mes = fecha.getMonthValue();
    private Integer anio = fecha.getYear();
    @Override
    @Transactional
    public ResponseEntity<?> createMicroemprendimiento(MicroemprendimientoRequest microemprendimientoRequest) {
        Usuario usuario = utilsMicroemprendimiento.findUsuario(microemprendimientoRequest.getEmail());
        utilsMicroemprendimiento.validationImage(microemprendimientoRequest.getImages());

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
        Usuario usuario = utilsMicroemprendimiento.findUsuario(microemprendimientoRequest.getEmail());
        utilsMicroemprendimiento.validationImage(microemprendimientoRequest.getImages());

        Microemprendimiento editMicroemprendimiento = microemprendimientoRepository.findById(idMicroemprendimiento)
                        .orElseThrow( () -> new EntityNotFoundException("Microemprendimiento not found with id: " + idMicroemprendimiento));
        if (!Objects.equals(usuario.getEmail(), editMicroemprendimiento.getUsuario().getEmail())){
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
    public ResponseEntity<?> findAllMicroemprendimientos() {
        List<Microemprendimiento> microemprendimientoList =
                microemprendimientoRepository.findAllByDeletedFalse();
        if (microemprendimientoList.isEmpty()) {
            throw new MicroemprendimientoException("No se encontraron microemprendimientos");
        }
        return ResponseEntity.status(HttpStatus.OK)
                        .body(MapperUtil.toDTOList(microemprendimientoList, MicroemprendimientoResponse.class));
    }
    @Transactional
    @Override
    public ResponseEntity<?> findByRubro(Long idRubro) {
        Rubro rubro = utilsMicroemprendimiento.findRubro(idRubro);
        List<Microemprendimiento> microemprendimientoList = microemprendimientoRepository.findByRubroAndDeletedFalse(rubro);
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
    public ResponseEntity<?> estadisticas(AdminRequest adminRequest) {
        List<Object[]> resultados = microemprendimientoRepository.estadisticas(adminRequest.getEmail(), anio, mes);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.objectToEstadisticaDTO(resultados));
    }
    @Override
    public ResponseEntity<?> findByUser(AdminRequest adminRequest) {
        List<Microemprendimiento> microemprendimientoList =
                microemprendimientoRepository.findAllByUsuarioEmailAndDeletedFalse(adminRequest.getEmail());
        if(microemprendimientoList.isEmpty()){
            throw new EntityNotFoundException("No se encontraron microemprendimientos asociados a este usuario " + adminRequest.getEmail());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(MapperUtil.toDTOList(microemprendimientoList, MicroemprendimientoResponse.class));
    }
}