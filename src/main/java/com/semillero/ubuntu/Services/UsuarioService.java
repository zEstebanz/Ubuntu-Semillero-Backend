package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(Usuario usuarioDTO) throws Exception {
        try {
            this.usuarioRepository.save(usuarioDTO);
            return usuarioDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void update(Long id, Usuario usuarioDTO) throws Exception {  //Ver si se puede optimizar esto
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setRol(usuarioDTO.getRol());
            usuario.setEmail(usuarioDTO.getEmail());            //Esto puede traer errores despues
            usuario.setTelefono(usuarioDTO.getTelefono());
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
