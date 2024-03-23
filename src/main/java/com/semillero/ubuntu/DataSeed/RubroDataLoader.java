package com.semillero.ubuntu.DataSeed;

import com.semillero.ubuntu.Entities.Rubro;
import com.semillero.ubuntu.Repositories.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RubroDataLoader implements CommandLineRunner {
    @Autowired
    RubroRepository rubroRepository;
    @Override
    public void run(String... args) throws Exception {
        loadRubroData();
    }

    public void loadRubroData() {
        if (rubroRepository.count() == 0) {
            Rubro rubro1 = new Rubro(1L, "Economía social/Desarrollo local/Inclusión financiera");
            Rubro rubro2 = new Rubro(2L, "Agroecología/Orgánicos/Alimentación saludables");
            Rubro rubro3 = new Rubro(3L, "Conservación/Regeneración/Servicios ecosistémicos");
            Rubro rubro4 = new Rubro(4L, "Empresas/Organismos de impacto/Economía circular");
            rubroRepository.save(rubro1);
            rubroRepository.save(rubro2);
            rubroRepository.save(rubro3);
            rubroRepository.save(rubro4);
        }
    }
}
