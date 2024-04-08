package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.AdminRequest;
import com.semillero.ubuntu.DTOs.RubroDTO;
import com.semillero.ubuntu.DTOs.RubroResponse;
import com.semillero.ubuntu.Entities.Rubro;
import com.semillero.ubuntu.Repositories.RubroRepository;
import com.semillero.ubuntu.Services.RubroService;
import com.semillero.ubuntu.Utils.Mapper;
import com.semillero.ubuntu.Utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RubroServiceImpl implements RubroService {

    private final RubroRepository rubroRepository;
    private LocalDate fecha = LocalDate.now();
    private Integer mes = fecha.getMonthValue();
    private Integer anio = fecha.getYear();
    @Override
    public List<RubroDTO> getAllRubros() {
        List<Rubro> rubrosList = rubroRepository.findAll();
        return MapperUtil.toDTOList(rubrosList, RubroDTO.class);
    }

    @Override
    public List<RubroResponse> estadisticasPorUsuario(AdminRequest adminRequest) {
        List<Object[]> resultados = rubroRepository.estadisticasPorUsuario(adminRequest.getEmail(), anio, mes);
        return Mapper.objectToRubroDTO(resultados);
    }
}