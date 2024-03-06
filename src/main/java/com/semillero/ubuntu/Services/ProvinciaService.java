package com.semillero.ubuntu.Services;


import com.semillero.ubuntu.DTOs.ProvinciaDTO;

import java.util.List;

public interface ProvinciaService {
    List<ProvinciaDTO> getProvincias(Long id);
}
