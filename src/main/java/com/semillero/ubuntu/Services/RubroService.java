package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.AdminRequest;
import com.semillero.ubuntu.DTOs.RubroDTO;
import com.semillero.ubuntu.DTOs.RubroResponse;

import java.util.List;

public interface RubroService {
    List<RubroDTO> getAllRubros();
    List<RubroResponse> estadisticasPorUsuario(AdminRequest adminRequest);
}
