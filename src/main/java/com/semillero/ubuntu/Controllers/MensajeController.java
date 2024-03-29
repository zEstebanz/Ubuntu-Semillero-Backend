package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.MensajeEstadisticaDTO;
import com.semillero.ubuntu.DTOs.MensajeRequestDTO;
import com.semillero.ubuntu.DTOs.MensajeResponseDTO;
import com.semillero.ubuntu.Services.impl.MensajeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensaje")
public class MensajeController {
    private final MensajeServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<MensajeResponseDTO> save(@Valid @RequestBody MensajeRequestDTO requestDTO) throws Exception {
        MensajeResponseDTO responseDTO=service.save(requestDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }
    @GetMapping
    public ResponseEntity<List<MensajeResponseDTO>>getAll(){
        List<MensajeResponseDTO>list=service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/gestionado")
    public ResponseEntity<List<MensajeResponseDTO>>getAllByGestionado(@RequestParam boolean gestionado){
        List<MensajeResponseDTO>list=service.getAllByGestionado(gestionado);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponseDTO>editGestionado(@PathVariable Long id, @RequestParam boolean gestionado){
         return ResponseEntity.ok(service.editGestionado(id, gestionado));

    }
    @GetMapping("/estadistica")
    public ResponseEntity<MensajeEstadisticaDTO> getEstadistica(){
        return ResponseEntity.ok(this.service.getEstadistica());
    }

}
