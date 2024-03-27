package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByGestionado(boolean gestionado);

    @Query(value = "SELECT COUNT (m.gestionado) FROM Mensaje m WHERE YEAR (m.fechaCreacion) = :anio AND MONTH(m.fechaCreacion) = :mes AND m.gestionado=true" )
    long countByFechaCreacionAndGestionado(@Param("anio") Integer anio, @Param("mes") Integer mes);

    @Query(value = "SELECT COUNT (m.gestionado) FROM Mensaje m WHERE YEAR (m.fechaCreacion) = :anio AND MONTH(m.fechaCreacion) = :mes AND m.gestionado=false" )
    long countByFechaCreacionAndNoGestionado(@Param("anio") Integer anio, @Param("mes") Integer mes);
}
