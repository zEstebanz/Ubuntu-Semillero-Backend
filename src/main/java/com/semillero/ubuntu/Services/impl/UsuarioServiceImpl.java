package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.UsuarioDTO;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Services.UsuarioService;
import com.semillero.ubuntu.Utils.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Creación de usuario para persistirlo en la base de datos
     * <p>
     * ROL: SUPER ADMIN (El rol solo puede ser Administrador o Inversor por ahora)
     */
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

    /**
     * Edición de los datos del usuario
     * <p>
     * ROL: Cualquiera
     */
    @Transactional
    public UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario ActUsuario = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id)
        );
        ActUsuario.setNombre(usuarioDTO.getNombre());
        ActUsuario.setApellido(usuarioDTO.getApellido());
        ActUsuario.setRol(usuarioDTO.getRol());
        usuarioRepository.save(ActUsuario);
        return MapperUtil.mapToDto(ActUsuario, UsuarioDTO.class);
    }

    /**
     * Baja lógica del usuario
     * <p>
     * Actualización: modificado para ocultar o activar el usuario (sujeto a cambios también)
     * <p>
     * ROL: ADMIN
     */
    @Transactional
    public void bajaLogica(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id)
        );
        usuario.setIsDeleted(!usuario.getIsDeleted());
        usuarioRepository.save(usuario);
    }
}
