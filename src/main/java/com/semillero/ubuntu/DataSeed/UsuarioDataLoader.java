package com.semillero.ubuntu.DataSeed;

import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Enums.Rol;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDataLoader implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public void run(String... args) throws Exception {
        loadUsuarioData();
    }

    private void loadUsuarioData() {
        if (usuarioRepository.count() == 0) {
            Usuario usuario1 = new Usuario(1L,"Ubuntu", "Semillero", "ubuntusemillero@gmail.com", false, Rol.ADMINISTRADOR);
            Usuario usuario2 = new Usuario(2L,"Flor", "Fernandez", "florenciafernandez0301@gmail.com", false, Rol.INVERSOR);
            Usuario usuario3 = new Usuario(3L,"Nahuel", "Rocha", "rocha.nahuel2024@gmail.com", false, Rol.ADMINISTRADOR);
            Usuario usuario4 = new Usuario(4L,"Nicolas", "Voloschin", "nicovolos@gmail.com", false, Rol.ADMINISTRADOR);
            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);
            usuarioRepository.save(usuario3);
            usuarioRepository.save(usuario4);
        }
    }
}
