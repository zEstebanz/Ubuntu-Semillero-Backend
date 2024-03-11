package com.semillero.ubuntu.Services.impl;


import com.semillero.ubuntu.DTOs.ProvinciaDTO;
import com.semillero.ubuntu.Entities.Provincia;
import com.semillero.ubuntu.Repositories.ProvinciaRepository;
import com.semillero.ubuntu.Services.ProvinciaService;
import com.semillero.ubuntu.Utils.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinciaServiceImpl implements ProvinciaService {

    private final ProvinciaRepository provinciaRepository;
    @Override
    public List<ProvinciaDTO> getProvincias(Long id) throws EntityNotFoundException {
        List<Provincia> provinciaList = provinciaRepository.findByIdPais(id);
        if(provinciaList.isEmpty()){
            throw new EntityNotFoundException("No provinces were found for the country with ID: "+ id);
        }
        return MapperUtil.toDTOList(provinciaList, ProvinciaDTO.class);
    }
}