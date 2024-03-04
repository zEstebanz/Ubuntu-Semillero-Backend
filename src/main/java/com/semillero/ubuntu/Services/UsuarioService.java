package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception;

    void bajaLogica(Long id) throws Exception;
}
