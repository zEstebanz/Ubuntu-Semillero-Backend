package com.semillero.ubuntu.Services.impl;


import com.semillero.ubuntu.DTOs.PaisDTO;
import com.semillero.ubuntu.Entities.Pais;
import com.semillero.ubuntu.Repositories.PaisRepository;
import com.semillero.ubuntu.Services.PaisService;
import com.semillero.ubuntu.Utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {
    @Autowired
    private PaisRepository paisRepository;
    @Override
    public List<PaisDTO> getAllPaises() {
        List<Pais> paisList = paisRepository.findAll();
        return MapperUtil.toDTOList(paisList, PaisDTO.class);
    }
}