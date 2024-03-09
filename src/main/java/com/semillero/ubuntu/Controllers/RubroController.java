package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.RubroDTO;
import com.semillero.ubuntu.Services.RubroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rubros")
@RequiredArgsConstructor
public class RubroController {

    private final RubroService rubroService;
    @GetMapping("/get-all")
    public ResponseEntity<List<RubroDTO>> getAllRubros() {
        List<RubroDTO> rubroList = rubroService.getAllRubros();
        return ResponseEntity.ok(rubroList);
    }
}
