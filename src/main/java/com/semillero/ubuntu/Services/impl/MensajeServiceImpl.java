package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.MensajeRequestDTO;
import com.semillero.ubuntu.DTOs.MensajeResponseDTO;
import com.semillero.ubuntu.Entities.Mensaje;
import com.semillero.ubuntu.Repositories.MensajeRepository;
import com.semillero.ubuntu.Services.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {
    private final MensajeRepository repository;

    @Override
    public MensajeResponseDTO save(MensajeRequestDTO requestDTO) throws Exception {
        try{
            Mensaje entityToSave=new Mensaje(requestDTO);
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

}
