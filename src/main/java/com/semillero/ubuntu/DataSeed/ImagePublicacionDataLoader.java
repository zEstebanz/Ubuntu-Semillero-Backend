package com.semillero.ubuntu.DataSeed;

import com.semillero.ubuntu.Entities.Image;
import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Repositories.ImageRepository;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ImagePublicacionDataLoader implements CommandLineRunner {
    private final ImageRepository imageRepository;
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        loadImageAndPublicacionData();
    }
    public void loadImageAndPublicacionData(){
        if(publicacionRepository.count()==0){
            Image img1=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1713370551/optghflzzfzi5vqobw6y.jpg","jpg", "2024-04-17T16:15:51Z","optghflzzfzi5vqobw6y",1200,600);
            Image img2=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1712248319/odekwyanggbisimwybyr.jpg","jpg","2024-04-04T16:31:59Z", "odekwyanggbisimwybyr",304,166);
            Image img3=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1712248452/yrucybwgamam4wtxj3mn.jpg","jpg","2024-04-04T16:34:12Z", "yrucybwgamam4wtxj3mn",1706,960);
            Image img4=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1712248526/vusloil5ybdi0tc3jas4.jpg","jpg","2024-04-04T16:35:26Z", "vusloil5ybdi0tc3jas4",1200,1200);
            Image img5=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1712248584/beqtssv6x078fn1fk87j.jpg","jpg","2024-04-04T16:36:24Z", "beqtssv6x078fn1fk87j",900,596);
            Image img6=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1712248639/qdtbn7hpmqo4ktyfpbgk.jpg","jpg","2024-04-04T16:37:19Z", "qdtbn7hpmqo4ktyfpbgk",1280,720);

            imageRepository.save(img1);
            imageRepository.save(img2);
            imageRepository.save(img3);


            Usuario user1=usuarioRepository.findById(1L).orElseThrow(()-> new EntityNotFoundException("No user was found with that id"));
            Usuario user2=usuarioRepository.findById(2L).orElseThrow(()-> new EntityNotFoundException("No user was found with that id"));

            Publicacion publi1= Publicacion.builder()
                    .id(1L)
                    .titulo("Inversiones Éticas: Más que ganancias")
                    .descripcion("Las decisiones financieras han trascendido la mera maximización del rendimiento. Actualmente, muchos inversores desean que sus decisiones reflejen sus valores éticos y morales, dando lugar a las inversiones éticas o sostenibles.")
                    .isDeleted(false)
                    .fechaCreacion(LocalDate.now())
                    .usuarioCreador(user1)
                    .cantVistas(2)
                    .build();
            publi1.addImage(img1);
            publi1.addImage(img2);

            Publicacion publi2= Publicacion.builder()
                    .id(2L)
                    .titulo("Descubre el Poder de las Inversiones con Propósito")
                    .descripcion("En el mundo de las finanzas, las inversiones éticas están demostrando ser mucho más que simples decisiones financieras. Están marcando el comienzo de una nueva era, donde el propósito y los valores se entrelazan con el potencial de ganancias. Cada vez más inversores están reconociendo que sus decisiones pueden tener un impacto más allá de sus estados de cuenta. Optan por inversiones que no solo buscan rendimientos financieros, sino también contribuir al bienestar de la sociedad y del planeta.")
                    .isDeleted(false)
                    .fechaCreacion(LocalDate.now())
                    .usuarioCreador(user2)
                    .cantVistas(0)
                    .build();
            publi2.addImage(img3);
            publi2.addImage(img4);
            publi2.addImage(img5);

            Publicacion publi3= Publicacion.builder()
                    .id(3L)
                    .titulo("Cómo las inversiones éticas están cambiando el mundo")
                    .descripcion("Las finanzas serán verdes o no serán. O dicho de otra manera, la sociedad empieza a tener claro que las inversiones éticas o sostenibles son las mejores opciones para buscar oportunidades de rentabilidad a largo plazo conforme a principios de responsabilidad social y protección medioambiental. En la década de los 70, aparecieron los primeros fondos de inversión sostenible, y hoy representan una demanda creciente en el mercado. Su importancia no responde únicamente a cuestiones individuales o de rentabilidad, sino que pone en valor la conciencia de comunidad y el deseo de dejar un mundo mejor a las futuras generaciones.")
                    .isDeleted(false)
                    .fechaCreacion(LocalDate.now())
                    .usuarioCreador(user1)
                    .cantVistas(0)
                    .build();
            publi3.addImage(img6);


            publicacionRepository.save(publi1);
            publicacionRepository.save(publi2);
            publicacionRepository.save(publi3);



        }
    }


}
