package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository <Publicacion, Long> {
    @Query(value = "SELECT * FROM publicacion WHERE baja = FALSE", nativeQuery = true)
    List<Publicacion> TraerPublicaciones();

    //Traer las 3 publicaciones más recientes y enviarlas a través de un endpoint
    @Query(value = "SELECT * FROM publicacion ORDER BY fecha_creacion DESC LIMIT 3", nativeQuery = true)
    List<Publicacion> TraerUltimasTres();

    @Query("SELECT COUNT(p) FROM Publicacion p WHERE p.fechaCreacion >= :fechaInicio AND p.fechaCreacion <= :fechaFin")
    long countByFechaCreacionBetween(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    @Query(value = "SELECT * FROM publicacion ORDER BY fecha_creacion DESC LIMIT 10", nativeQuery = true)
    List<Publicacion> TraerUltimasDiez();

}
