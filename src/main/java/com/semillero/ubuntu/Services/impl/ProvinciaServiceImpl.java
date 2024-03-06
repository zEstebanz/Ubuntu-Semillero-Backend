package com.semillero.ubuntu.Services.impl;


import com.semillero.ubuntu.DTOs.ProvinciaDTO;
import com.semillero.ubuntu.Entities.Provincia;
import com.semillero.ubuntu.Repositories.ProvinciaRepository;
import com.semillero.ubuntu.Services.ProvinciaService;
import com.semillero.ubuntu.Utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Override
    public List<ProvinciaDTO> getProvincias(Long id) {
        List<Provincia> provinciaList = provinciaRepository.findByIdPais(id);
        return MapperUtil.toDTOList(provinciaList, ProvinciaDTO.class);
    }
}