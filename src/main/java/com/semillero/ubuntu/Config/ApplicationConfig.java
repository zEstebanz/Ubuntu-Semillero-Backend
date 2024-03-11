package com.semillero.ubuntu.Config;

import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Security.userauth.UserAuth;
import jakarta.persistence.EntityNotFoundException;
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
                    .orElseThrow(() -> new EntityNotFoundException("User not found with: " + username));

            return new UserAuth(usuario);
        };
    }
}
