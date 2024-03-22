package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Utils.UsuarioProjection;
import com.semillero.ubuntu.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    List<UsuarioProjection> findNombreAndEmailByIsDeletedFalse();
}
