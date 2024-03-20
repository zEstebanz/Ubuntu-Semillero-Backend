package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.InversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;

import java.util.List;

public interface InversionService {
    CalculoInversionDTO calcularInversion(RecibirInversionDTO recibirInversionDTO) throws Exception;

    List<InversionDTO> getAll() throws Exception;

    List<InversionDTO> buscarPorUsuarioId(Long id) throws Exception;

    void guardarInversion(CalculoInversionDTO calculoInversionDTO) throws Exception;
}
