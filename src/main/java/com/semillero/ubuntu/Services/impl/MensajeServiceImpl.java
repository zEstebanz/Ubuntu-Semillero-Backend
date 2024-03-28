package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.MensajeEstadisticaDTO;
import com.semillero.ubuntu.DTOs.MensajeRequestDTO;
import com.semillero.ubuntu.DTOs.MensajeResponseDTO;
import com.semillero.ubuntu.Entities.Mensaje;
import com.semillero.ubuntu.Entities.Microemprendimiento;
import com.semillero.ubuntu.Repositories.MensajeRepository;
import com.semillero.ubuntu.Repositories.MicroemprendimientoRepository;
import com.semillero.ubuntu.Services.MensajeService;
import com.semillero.ubuntu.Utils.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {
    private final MensajeRepository repository;
    private final MicroemprendimientoRepository microemprendimientoRepository;

    @Override
    @Transactional
    public MensajeResponseDTO save(MensajeRequestDTO requestDTO) throws EntityNotFoundException {
            Microemprendimiento microemprendimiento=microemprendimientoRepository.findById(requestDTO.getId_microemprendimiento())
                    .orElseThrow(()->new EntityNotFoundException("No microentrepreneurship was found with ID: "+ requestDTO.getId_microemprendimiento()));
            Mensaje entityToSave=new Mensaje(requestDTO);
            entityToSave.setMicroemprendimiento(microemprendimiento);
            Mensaje entitySaved= repository.save(entityToSave);
            return Mapper.mensajeToResponse(entitySaved);
    }
    @Override
    public List<MensajeResponseDTO> getAll() {
        List<Mensaje>list=repository.findAll();
        return list.stream().map(Mapper::mensajeToResponse).toList();
    }
    @Override
    public List<MensajeResponseDTO>getAllByGestionado(boolean gestionado){
        List<Mensaje>list=repository.findByGestionado(gestionado);
        return list.stream().map(Mapper::mensajeToResponse).toList();
    }

    @Transactional
    @Override
    public MensajeResponseDTO editGestionado(Long id, boolean gestionado) throws EntityNotFoundException{
        Mensaje mensajeToEdit=repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No message was found with ID: "+id));
        mensajeToEdit.setGestionado(gestionado);
        Mensaje mensajeEdited=repository.save(mensajeToEdit);
        return Mapper.mensajeToResponse(mensajeEdited);
    }

    @Override
    public MensajeEstadisticaDTO getEstadistica(){
        LocalDate date = LocalDate.now();
        Integer month = date.getMonthValue();
        Integer year = date.getYear();
        Tuple counts=this.repository.countByFechaCreacionAndGestionado(year, month);
        return Mapper.tupleToMensajeEstadisticaDTO(counts);
    }

}
