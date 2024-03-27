package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.MensajeRequestDTO;
import com.semillero.ubuntu.DTOs.MensajeResponseDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface MensajeService {
    MensajeResponseDTO save(MensajeRequestDTO requestDTO) throws EntityNotFoundException;
    List<MensajeResponseDTO> getAll();
    List<MensajeResponseDTO>getAllByGestionado(boolean gestionado);
    MensajeResponseDTO editGestionado(Long id, boolean gestionado) throws EntityNotFoundException;
    long countByFechaCreacionAndNoGestionado();

}
