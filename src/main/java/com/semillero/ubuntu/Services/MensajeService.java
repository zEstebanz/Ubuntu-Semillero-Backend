package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.MensajeRequestDTO;
import com.semillero.ubuntu.DTOs.MensajeResponseDTO;

import java.util.List;

public interface MensajeService {
    MensajeResponseDTO save(MensajeRequestDTO requestDTO) throws Exception;
    List<MensajeResponseDTO> getAll();

}
