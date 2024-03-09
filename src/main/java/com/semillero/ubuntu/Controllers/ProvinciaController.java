package com.semillero.ubuntu.Controllers;


import com.semillero.ubuntu.DTOs.ProvinciaDTO;
import com.semillero.ubuntu.Exceptions.provincia.ProvinciaNotFoundException;
import com.semillero.ubuntu.Services.ProvinciaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provincias")
@RequiredArgsConstructor
public class ProvinciaController {

    private final ProvinciaService provinciaService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getProvincias(@PathVariable("id") Long idPais) throws Exception{
        List<ProvinciaDTO> provinciaList = provinciaService.getProvincias(idPais);
        return ResponseEntity.ok(provinciaList);
    }
}