package com.semillero.ubuntu.ChatBot.Controllers;

import com.semillero.ubuntu.ChatBot.DTOs.PreguntaDTO;
import com.semillero.ubuntu.ChatBot.Services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/FAQ")
public class PreguntaController {
    @Autowired
    private PreguntaService preguntaService;

    @GetMapping("")
    public ResponseEntity<?> getInicial() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(preguntaService.getInicial());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay preguntas");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PreguntaDTO preguntaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(preguntaService.create(preguntaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede crear pregunta");
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id,@RequestBody PreguntaDTO preguntaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(preguntaService.edit(id, preguntaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede editar pregunta");
        }
    }

    @PutMapping("baja/{id}")
    public ResponseEntity<?> bajaLogica(@PathVariable Long id, @RequestBody PreguntaDTO preguntaDTO) {
        try {
            preguntaService.bajaLogica(id, preguntaDTO);
            return ResponseEntity.ok("Baja correcta");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede bajar pregunta");
        }
    }
}
