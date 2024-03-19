package com.semillero.ubuntu.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Security.userauth.UserAuth;
import com.semillero.ubuntu.Utils.CloudinaryKey;
import com.semillero.ubuntu.Utils.CloudinaryName;
import com.semillero.ubuntu.Utils.CloudinarySecret;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsuarioRepository usuarioRepository;
    private final CloudinaryKey cloudinaryKey;
    private final CloudinaryName cloudinaryName;
    private final CloudinarySecret cloudinarySecret;
    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {
            Usuario usuario = usuarioRepository.findByEmail(username)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with: " + username));

            return new UserAuth(usuario);
        };
    }
    @Bean
    public Cloudinary getCloudinary(){

        Map<String,Object> config = new HashMap<>();
        config.put("cloud_name", cloudinaryName.getCLOUD_NAME());
        config.put("api_key", cloudinaryKey.getCLOUD_API_KEY());
        config.put("api_secret", cloudinarySecret.getCLOUD_API_SECRET());
        config.put("secure", true);

        return new Cloudinary(config);
    }
}
