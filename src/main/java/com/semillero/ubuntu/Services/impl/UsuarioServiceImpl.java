package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.UsuarioDTO;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Services.UsuarioService;
import com.semillero.ubuntu.Utils.MapperUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
        try {
            Usuario nuevoUsuario = Usuario.builder()
                    .nombre(usuarioDTO.getNombre())
                    .apellido(usuarioDTO.getApellido())
                    .rol(usuarioDTO.getRol())
                    .email(usuarioDTO.getEmail())
                    .isDeleted(false)
                    .build();
            return MapperUtil.mapToDto(nuevoUsuario, UsuarioDTO.class);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Transactional
    public UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception {  //Ver si se puede optimizar esto
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
                //ver si puedo colocar el .isPresent()
                Usuario ActUsuario = usuarioOptional.get();
                ActUsuario.setNombre(usuarioDTO.getNombre());
                ActUsuario.setApellido(usuarioDTO.getApellido());
                ActUsuario.setRol(usuarioDTO.getRol());
                ActUsuario.setEmail(usuarioDTO.getEmail());            //Esto puede traer errores
                usuarioRepository.save(ActUsuario);
                return MapperUtil.mapToDto(ActUsuario, UsuarioDTO.class);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void bajaLogica(Long id) throws Exception {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (usuarioOptional.isPresent()) {
                Usuario bajaUsuario = usuarioOptional.get();
                bajaUsuario.setIsDeleted(true);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
