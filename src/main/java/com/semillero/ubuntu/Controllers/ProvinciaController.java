package com.semillero.ubuntu.Controllers;


import com.semillero.ubuntu.DTOs.ProvinciaDTO;
import com.semillero.ubuntu.Services.ProvinciaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController {
    @Autowired
    ProvinciaService provinciaService;
    @GetMapping("/{id}")
    public ResponseEntity<List<ProvinciaDTO>> getProvincias(@PathVariable("id") Long idPais){
        List<ProvinciaDTO> provinciaList = provinciaService.getProvincias(idPais);
        return ResponseEntity.ok(provinciaList);
    }
}