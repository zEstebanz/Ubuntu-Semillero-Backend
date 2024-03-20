package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;

public interface InversionService {
    CalculoInversionDTO calcularInversion (RecibirInversionDTO recibirInversionDTO) throws Exception;
}
