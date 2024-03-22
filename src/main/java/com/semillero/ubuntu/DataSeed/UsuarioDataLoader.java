package com.semillero.ubuntu.DataSeed;

import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Enums.Rol;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioDataLoader implements CommandLineRunner {

     private final UsuarioRepository usuarioRepository;
     private final ImagePublicacionDataLoader imagePublicacionDataLoader;
    @Override
    public void run(String... args) throws Exception {
        loadUsuarioData();
        imagePublicacionDataLoader.loadImageAndPublicacionData();
    }

    private void loadUsuarioData() {
        if (usuarioRepository.count() == 0) {
            Usuario usuario1 = new Usuario(1L,"Ubuntu", "Semillero", "ubuntusemillero@gmail.com", false, Rol.ADMINISTRADOR);
            Usuario usuario2 = new Usuario(2L,"Flor", "Fernandez", "florenciafernandez0301@gmail.com", false, Rol.ADMINISTRADOR);
            Usuario usuario3 = new Usuario(3L,"Nahuel", "Rocha", "turkito.-@hotmail.com.ar", false, Rol.ADMINISTRADOR);
            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);
            usuarioRepository.save(usuario3);
        }
    }
}
