package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository <Publicacion, Long> {
    @Query(value = "SELECT * FROM publicacion WHERE baja = 0", nativeQuery = true)
    List<Publicacion> TraerPublicaciones();
}
