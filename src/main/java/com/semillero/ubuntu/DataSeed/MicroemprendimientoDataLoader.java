package com.semillero.ubuntu.DataSeed;

import com.semillero.ubuntu.Entities.*;
import com.semillero.ubuntu.Repositories.*;
import com.semillero.ubuntu.Services.UtilsMicroemprendimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MicroemprendimientoDataLoader implements CommandLineRunner {
    
    private final MicroemprendimientoRepository microemprendimientoRepository;

    private final UtilsMicroemprendimiento utilsMicroemprendimiento;

    @Override
    public void run(String... args) throws Exception {
        loadMicroemprendimientoWithImages();
    }

    public void loadMicroemprendimientoWithImages() {
        if (microemprendimientoRepository.count() == 0) {
            List<Map> listImages = crearImagenes();
            List<Image> images = utilsMicroemprendimiento.saveInBd(listImages);

            Rubro rubro = utilsMicroemprendimiento.findRubro(1L);
            Pais pais = utilsMicroemprendimiento.findPais(1L);
            Provincia provincia = utilsMicroemprendimiento.findProvincia(3L);
            Usuario usuario = utilsMicroemprendimiento.findUsuario("ubuntusemillero@gmail.com");

            Microemprendimiento microemprendimiento1 = new Microemprendimiento();
            microemprendimiento1.setId(1L);
            microemprendimiento1.setNombre("EcoSenda");
            microemprendimiento1.setRubro(rubro);
            microemprendimiento1.setSubrubro("Finca agroecológica");
            microemprendimiento1.setPais(pais);
            microemprendimiento1.setProvincia(provincia);
            microemprendimiento1.setCiudad("Tunuyán");
            microemprendimiento1.setDescripcion("Promueven un modelo de agricultura sostenible, protegiendo " +
                    "el medio ambiente, el agua y las semillas autóctonas. Cultivan frutas, verduras, plantas medicinales " +
                    "y crean derivados. Editan también contenidos educativos, gestionando un banco de semillas y comercializan " +
                    "o intercambian excedentes.");
            microemprendimiento1.setMasInfo("Nació del sueño de restaurar la salud y adoptar un estilo de vida ideal. " +
                    "Este proyecto familiar creció fundamentado en la permacultura, comprometiéndose con la soberanía alimentaria, " +
                    "el bienestar, el regreso al campo, la venta directa y la dignidad de la vida campesina.");
            microemprendimiento1.setDeleted(false);
            microemprendimiento1.setGestionado(false);
            microemprendimiento1.setUsuario(usuario);
            microemprendimiento1.setFechaCreacion(LocalDate.now());
            microemprendimiento1.setImages(images);
            microemprendimientoRepository.save(microemprendimiento1);
        }
    }
    private List<Map> crearImagenes() {
        return Arrays.asList(
                Map.of(
                        "secure_url", "https://res.cloudinary.com/dvoxzrkzs/image/upload/v1711151302/cfedv9xifayd7wyvahnv.jpg",
                        "format", "jpg",
                        "created_at", "2024-03-22T23:48:22Z",
                        "public_id", "cfedv9xifayd7wyvahnv",
                        "width", 2731,
                        "height", 4096
                ),
                Map.of(
                        "secure_url", "https://res.cloudinary.com/dvoxzrkzs/image/upload/v1711151306/hrj91ijz5kfnyxokrlsz.jpg",
                        "format", "jpg",
                        "created_at", "2024-03-22T23:48:26Z",
                        "public_id", "cfedv9xifayd7wyvahnv",
                        "width", 2731,
                        "height", 4096
                ),
                Map.of(
                        "secure_url", "https://res.cloudinary.com/dvoxzrkzs/image/upload/v1711151310/zo7xeqcvqvu3qtn7k7s7.jpg",
                        "format", "jpg",
                        "created_at", "2024-03-22T23:48:30Z",
                        "public_id", "cfedv9xifayd7wyvahnv",
                        "width", 3277,
                        "height", 4096
                )
        );
    }
}
