package com.semillero.ubuntu.Security;

import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Exceptions.usuario.UserNotFoundException;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Security.userauth.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsuarioRepository usuarioRepository;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {
            Usuario usuario = usuarioRepository.findByEmail(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found with: " + username));

            return new UserAuth(usuario);
        };
    }


}
