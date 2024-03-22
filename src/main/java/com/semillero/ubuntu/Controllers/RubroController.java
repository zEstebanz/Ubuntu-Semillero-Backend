package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.RubroDTO;
import com.semillero.ubuntu.Services.RubroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rubros")
@RequiredArgsConstructor
public class RubroController {

    private final RubroService rubroService;
    @GetMapping("/get-all")
    public ResponseEntity<List<RubroDTO>> getRubrosOrderByCantMicroemprendimientos() {
        List<RubroDTO> rubroList = rubroService.getRubrosOrderByCantMicroemprendimientos();
        return ResponseEntity.ok(rubroList);
    }
    @GetMapping("/admin/estadisticasPorUsuario/{idUsuario}")
    public ResponseEntity<List<RubroDTO>> estadisticasPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
        List<RubroDTO> rubroList = rubroService.estadisticasPorUsuario(idUsuario);
        return ResponseEntity.ok(rubroList);
    }
}
