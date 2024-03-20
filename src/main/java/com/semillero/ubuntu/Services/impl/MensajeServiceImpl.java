package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.MensajeRequestDTO;
import com.semillero.ubuntu.DTOs.MensajeResponseDTO;
import com.semillero.ubuntu.Entities.Mensaje;
import com.semillero.ubuntu.Entities.Microemprendimiento;
import com.semillero.ubuntu.Repositories.MensajeRepository;
import com.semillero.ubuntu.Repositories.MicroemprendimientoRepository;
import com.semillero.ubuntu.Services.MensajeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {
    private final MensajeRepository repository;
    private final MicroemprendimientoRepository microemprendimientoRepository;

    @Override
    @Transactional
    public MensajeResponseDTO save(MensajeRequestDTO requestDTO) throws Exception {
        try{
          /*  Microemprendimiento microemprendimiento=microemprendimientoRepository.findById(requestDTO.getId_microemprendimiento())
                    .orElseThrow(()->new EntityNotFoundException("No microentrepreneurship was found with ID: "+ requestDTO.getId_microemprendimiento()));*/
            Mensaje entityToSave=new Mensaje(requestDTO);
           /* entityToSave.setMicroemprendimiento(microemprendimiento);*/
            Mensaje entitySaved= repository.save(entityToSave);
            return new MensajeResponseDTO(entitySaved);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public List<MensajeResponseDTO> getAll() {
        List<Mensaje>list=repository.findAll();
        return list.stream().map(MensajeResponseDTO::new).toList();
    }
    @Override
    public List<MensajeResponseDTO>getAllByGestionado(boolean gestionado){
        List<Mensaje>list=repository.findByGestionado(gestionado);
        return list.stream().map(MensajeResponseDTO::new).toList();
    }

    @Transactional
    @Override
    public MensajeResponseDTO editGestionado(Long id, boolean gestionado){
        Mensaje mensajeToEdit=repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No message was found with ID: "+id));
        mensajeToEdit.setGestionado(gestionado);
        Mensaje mensajeEdited=repository.save(mensajeToEdit);
        return new MensajeResponseDTO(mensajeEdited);
    }



}
