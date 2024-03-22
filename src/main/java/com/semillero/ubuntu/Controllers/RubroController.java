package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.AdminRequest;
import com.semillero.ubuntu.DTOs.RubroDTO;
import com.semillero.ubuntu.DTOs.RubroResponse;
import com.semillero.ubuntu.Services.RubroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/admin/estadisticasPorUsuario")
    public ResponseEntity<List<RubroResponse>> estadisticasPorUsuario(@RequestBody AdminRequest adminRequest) {
        List<RubroResponse> rubroList = rubroService.estadisticasPorUsuario(adminRequest);
        return ResponseEntity.ok(rubroList);
    }
}
