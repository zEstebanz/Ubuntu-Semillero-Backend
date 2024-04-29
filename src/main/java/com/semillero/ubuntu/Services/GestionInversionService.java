package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.GestionInversionDTO;
import com.semillero.ubuntu.DTOs.InversionVisitanteDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;

import java.util.List;

public interface GestionInversionService {
    CalculoInversionDTO calcularInversion(RecibirInversionDTO recibirInversionDTO);

    GestionInversionDTO crearGestion(GestionInversionDTO gestionInversionDTO);

    //Le paso el id de Gestion como parametro dentro del DTO
    GestionInversionDTO editarGestion(GestionInversionDTO gestionInversionDTO) throws Exception;

    void logicaGestion(Long idMicro) throws Exception;

    GestionInversionDTO getInversion(Long idMicro) throws Exception;

    InversionVisitanteDTO getInversionVisitante(Long idMicro) throws Exception;
}
