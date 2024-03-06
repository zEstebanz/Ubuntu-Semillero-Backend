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

    private void loadPaisData() {
        if(paisRepository.count() == 0){
            Pais argentina = new Pais();
            argentina.setId(1L);
            argentina.setNombre("Argentina");
            paisRepository.save(argentina);
            cargarProvincias(argentina, Arrays.asList("Buenos Aires", "Cordoba", "Corrientes",
                    "Mendoza", "Formosa", "Tucuman"));

            Pais bolivia = new Pais();
            bolivia.setId(2L);
            bolivia.setNombre("Bolivia");
            paisRepository.save(bolivia);
            cargarProvincias(bolivia, Arrays.asList("Cochabamba", "La Paz", "Oruro",
                    "Potosí", "Santa Cruz", "Tarija"));

            Pais brasil = new Pais();
            brasil.setId(3L);
            brasil.setNombre("Brasil");
            paisRepository.save(brasil);
            cargarProvincias(brasil, Arrays.asList("Belo Horizonte", "Brasilia",
                    "Fortaleza", "Río de Janeiro", "Salvador", "São Paulo"));

            Pais chile = new Pais();
            chile.setId(4L);
            chile.setNombre("Chile");
            paisRepository.save(chile);
            cargarProvincias(chile, Arrays.asList("Antofagasta", "Concepción", "Santiago", "Valdivia",
                    "Valparaíso", "Viña del Mar"));

            Pais paraguay = new Pais();
            paraguay.setId(5L);
            paraguay.setNombre("Paraguay");
            paisRepository.save(paraguay);
            cargarProvincias(paraguay, Arrays.asList("Asunción", "Capiatá", "Ciudad del Este",
                    "Encarnación", "Luque", "San Pedro"));

            Pais uruguay = new Pais();
            uruguay.setId(6L);
            uruguay.setNombre("Uruguay");
            paisRepository.save(uruguay);
            cargarProvincias(uruguay, Arrays.asList("Canelones", "Maldonado", "Montevideo",
                    "Paysandú", "Rivera", "Salto"));
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