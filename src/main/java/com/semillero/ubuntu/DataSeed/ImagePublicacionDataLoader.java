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

            Publicacion publi1=new Publicacion(1L," Inversiones Éticas: Más que ganancias",
                    "Las decisiones financieras han trascendido la mera maximización del rendimiento. Actualmente, muchos inversores desean que sus decisiones reflejen sus valores éticos y morales, dando lugar a las inversiones éticas o sostenibles.\n" +
                            "\n" +
                            "Estas no solo evitan sectores polémicos como el tabaco o las armas; buscan respaldar empresas y proyectos que beneficien positivamente a la sociedad y al medio ambiente. Estas empresas suelen adherirse a altos estándares de responsabilidad social, considerando tanto a accionistas como a las comunidades en las que operan.\n" +
                            "\n" +
                            "El atractivo de las inversiones éticas radica en la posibilidad de generar un impacto positivo con el dinero invertido. Apoyando a empresas pioneras en energías renovables, que fomentan la igualdad de género o que practican la equidad laboral, los inversores no solo buscan ganancias, sino también cambios beneficiosos en el mundo.\n" +
                            "\n" +
                            "Contrario a lo que algunos podrían pensar, las inversiones éticas pueden ofrecer rendimientos competitivos. La demanda de soluciones sostenibles está en aumento, y las empresas que lideran en este ámbito suelen tener una ventaja competitiva a largo plazo.\n" +
                            "\n" +
                            "No obstante, es esencial investigar adecuadamente. No todas las empresas que se promocionan como \"sostenibles\" cumplen con estos criterios. Certificaciones, como los Principios de Inversión Responsable de las Naciones Unidas, son útiles para discernir el compromiso real de una empresa con la sostenibilidad.\n" +
                            "\n" +
                            "En conclusión, las inversiones éticas ofrecen la oportunidad de unir capital y valores. Al buscar un impacto positivo más allá de los rendimientos, contribuimos a un futuro más equitativo y sostenible.",
                    false, LocalDate.now(), Arrays.asList(img1, img2),user1,0);
            Publicacion publi2=new Publicacion(2L, "Inversiones éticas: más que ganancias",
                    "Las decisiones financieras han trascendido la mera maximización del rendimiento. Actualmente, muchos inversores desean que sus decisiones reflejen sus valores éticos y morales, dando lugar a las inversiones éticas o sostenibles.",
                    false, LocalDate.now(), Arrays.asList(img3), user2, 0);
            Publicacion publi3=new Publicacion(3L, "Inversiones éticas: más que ganancias",
                    "Las decisiones financieras han trascendido la mera maximización del rendimiento. Actualmente, muchos inversores desean que sus decisiones reflejen sus valores éticos y morales, dando lugar a las inversiones éticas o sostenibles.",
                    false, LocalDate.now(), Arrays.asList(img1,img2,img3), user1, 0);

            publicacionRepository.save(publi1);
            publicacionRepository.save(publi2);
            publicacionRepository.save(publi3);

        }
    }


}
