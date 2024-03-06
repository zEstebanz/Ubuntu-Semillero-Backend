package com.semillero.ubuntu.Controllers;


import com.semillero.ubuntu.DTOs.PaisDTO;
import com.semillero.ubuntu.Services.PaisService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {
    @Autowired
    PaisService paisService;
    @GetMapping("/get-all")
    public ResponseEntity<List<PaisDTO>> getAllPaises(){
        List<PaisDTO> paisList = paisService.getAllPaises();
        return ResponseEntity.ok(paisList);
    }
}