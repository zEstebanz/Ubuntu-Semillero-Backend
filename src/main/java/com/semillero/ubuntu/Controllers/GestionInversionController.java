package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.Services.impl.GestionInversionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/GestionInversion")
public class GestionInversionController {
    @Autowired
    private GestionInversionServiceImpl gestionInversionServiceImpl;
}
