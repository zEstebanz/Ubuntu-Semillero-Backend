package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.Repositories.InversionRepository;
import com.semillero.ubuntu.Services.InversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InversionServiceImpl implements InversionService {
    @Autowired
    private InversionRepository inversionRepository;
}
