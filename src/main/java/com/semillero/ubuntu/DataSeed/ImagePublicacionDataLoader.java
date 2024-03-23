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
import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class ImagePublicacionDataLoader implements CommandLineRunner {
    private final ImageRepository imageRepository;
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        //loadImageAndPublicacionData();
    }
    public void loadImageAndPublicacionData(){
        if(publicacionRepository.count()==0){
            Image img1=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1711129609/bboysq4bwc0koucf2dmh.jpg","jpg", "2024-03-20T16:15:08Z","bboysq4bwc0koucf2dmh",1080,675);
            Image img2=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1711129609/bboysq4bwc0koucf2dmh.jpg","jpg","2024-03-22T16:45:47Z", "bboysq4bwc0koucf2dmh",628,375);
            Image img3=new Image("https://res.cloudinary.com/dvoxzrkzs/image/upload/v1711129609/bboysq4bwc0koucf2dmh.jpg","jpg","2024-03-22T16:50:13Z", "bboysq4bwc0koucf2dmh",621,414);

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
            Publicacion publi2= Publicacion.builder()
                    .id(2L)
                    .titulo("Inversiones Éticas: Más que ganancias")
                    .descripcion("Las decisiones financieras han trascendido la mera maximización del rendimiento. Actualmente, muchos inversores desean que sus decisiones reflejen sus valores éticos y morales, dando lugar a las inversiones éticas o sostenibles.")
                    .isDeleted(false)
                    .fechaCreacion(LocalDate.now())
                    .usuarioCreador(user2)
                    .cantVistas(0)
                    .build();
            publi1.addImage(img2);
            Publicacion publi3= Publicacion.builder()
                    .id(3L)
                    .titulo("Inversiones Éticas: Más que ganancias")
                    .descripcion("Las decisiones financieras han trascendido la mera maximización del rendimiento. Actualmente, muchos inversores desean que sus decisiones reflejen sus valores éticos y morales, dando lugar a las inversiones éticas o sostenibles.")
                    .isDeleted(false)
                    .fechaCreacion(LocalDate.now())
                    .usuarioCreador(user1)
                    .cantVistas(0)
                    .build();
            publi1.addImage(img3);

            publicacionRepository.save(publi1);
            publicacionRepository.save(publi2);
            publicacionRepository.save(publi3);

        }
    }


}
