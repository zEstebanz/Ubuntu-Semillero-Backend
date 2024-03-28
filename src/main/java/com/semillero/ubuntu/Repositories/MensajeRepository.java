package com.semillero.ubuntu.Repositories;


import com.semillero.ubuntu.Entities.Mensaje;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByGestionado(boolean gestionado);
    @Query(value = "SELECT COUNT(CASE WHEN m.gestionado = true THEN 1 END) AS cantGestionados, COUNT(CASE WHEN m.gestionado = false THEN 1 END) AS cantNoGestionados FROM Mensaje m WHERE YEAR(m.fechaCreacion) = :year AND MONTH(m.fechaCreacion) = :month")
    Tuple countByFechaCreacionAndGestionado(@Param("year") Integer year, @Param("month") Integer month);
}
