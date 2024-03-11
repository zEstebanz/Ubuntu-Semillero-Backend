package com.semillero.ubuntu.Services;


import com.semillero.ubuntu.DTOs.ProvinciaDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ProvinciaService {
    List<ProvinciaDTO> getProvincias(Long id) throws EntityNotFoundException;
}
