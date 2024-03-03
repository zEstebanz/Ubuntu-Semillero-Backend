package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.RubroDTO;
import com.semillero.ubuntu.Entities.Rubro;
import com.semillero.ubuntu.Repositories.RubroRepository;
import com.semillero.ubuntu.Services.RubroService;
import com.semillero.ubuntu.Utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroServiceImpl implements RubroService {
    @Autowired
    private RubroRepository rubroRepository;

    @Override
    public List<RubroDTO> getAllRubros() {
        List<Rubro> rubrosList = rubroRepository.findAll();
        return MapperUtil.toDTOList(rubrosList, RubroDTO.class);
    }
}