package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
}
