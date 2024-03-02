package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository <Publicacion, Long> {
}
