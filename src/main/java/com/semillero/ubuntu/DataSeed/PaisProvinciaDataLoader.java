package com.semillero.ubuntu.DataSeed;


import com.semillero.ubuntu.Entities.Pais;
import com.semillero.ubuntu.Entities.Provincia;
import com.semillero.ubuntu.Repositories.PaisRepository;
import com.semillero.ubuntu.Repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PaisProvinciaDataLoader implements CommandLineRunner {
    @Autowired
    PaisRepository paisRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;
    @Override
    public void run(String... args) throws Exception {
        loadPaisData();
    }

    public void loadPaisData() {
        if(paisRepository.count() == 0){
            Pais argentina = new Pais();
            argentina.setId(1L);
            argentina.setNombre("Argentina");
            paisRepository.save(argentina);
            cargarProvincias(argentina, Arrays.asList("Buenos Aires", "Córdoba",
                    "Mendoza", "San Luis"));

            Pais chile = new Pais();
            chile.setId(2L);
            chile.setNombre("Chile");
            paisRepository.save(chile);
            cargarProvincias(chile, Arrays.asList("Antofagasta", "Santiago",
                    "Valparaíso", "Viña del Mar"));

            Pais colombia = new Pais();
            colombia.setId(3L);
            colombia.setNombre("Colombia");
            paisRepository.save(colombia);
            cargarProvincias(colombia, Arrays.asList("Bolivar", "La Guajira",
                    "Sucre", "Tolima"));

            Pais uruguay = new Pais();
            uruguay.setId(4L);
            uruguay.setNombre("Uruguay");
            paisRepository.save(uruguay);
            cargarProvincias(uruguay, Arrays.asList("Maldonado", "Montevideo",
                    "Rivera", "Salto"));
        }
    }
    private void cargarProvincias(Pais pais, List<String> nombresProvincias) {
        for (String nombreProvincia : nombresProvincias) {
            Provincia provincia = new Provincia();
            provincia.setNombre(nombreProvincia);
            provincia.setPais(pais);
            provinciaRepository.save(provincia);
        }
    }
}