package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.GestionInversionDTO;
import com.semillero.ubuntu.DTOs.InversionVisitanteDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;

import java.util.List;

public interface GestionInversionService {
    CalculoInversionDTO calcularInversion(RecibirInversionDTO recibirInversionDTO) throws Exception;

    List<GestionInversionDTO> getAll() throws Exception;

    GestionInversionDTO crearGestion(GestionInversionDTO gestionInversionDTO) throws Exception;

    //Por ahora solo le paso el id de Gestion como parametro aparte
    GestionInversionDTO editarGestion(GestionInversionDTO gestionInversionDTO) throws Exception;

    void logicaGestion(Long idMicro) throws Exception;

    GestionInversionDTO getInversion(Long idMicro) throws Exception;

    InversionVisitanteDTO getInversionVisitante(Long idMicro) throws Exception;
}
