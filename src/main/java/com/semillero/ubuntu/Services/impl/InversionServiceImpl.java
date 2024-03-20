package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;
import com.semillero.ubuntu.Repositories.InversionRepository;
import com.semillero.ubuntu.Services.InversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InversionServiceImpl implements InversionService {
    @Autowired
    private InversionRepository inversionRepository;

    //Repositorio Microemprendimiento

    @Override
    public CalculoInversionDTO calcularInversion(RecibirInversionDTO recibirInversionDTO) throws Exception {
        try {
            //Buscar por id el microemprendimiento

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
