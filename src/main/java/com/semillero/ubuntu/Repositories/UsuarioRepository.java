package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository <Usuario, Long>{
}
