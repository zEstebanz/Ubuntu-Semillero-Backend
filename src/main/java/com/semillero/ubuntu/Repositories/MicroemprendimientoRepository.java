package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Microemprendimiento;
import com.semillero.ubuntu.Entities.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MicroemprendimientoRepository extends JpaRepository<Microemprendimiento, Long> {
    List<Microemprendimiento> findAllByDeletedFalse();
    List<Microemprendimiento> findByRubroAndDeletedFalse(Rubro rubro);
    Optional<Microemprendimiento> findByIdAndDeletedFalse(Long id);
    @Query("SELECT COUNT(m) FROM Microemprendimiento m WHERE m.fechaCreacion >= :fechaInicio AND m.fechaCreacion <= :fechaFin")
    long countByFechaCreacionBetween(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
    @Query("SELECT COUNT(m) AS totalMicroemprendimientos " +
            "FROM Microemprendimiento m " +
            "WHERE YEAR(m.fechaCreacion) = :anio AND MONTH(m.fechaCreacion) = :mes AND m.deleted = false " +
            "AND m.usuario.email = :email")
    List<Object[]> estadisticas(@Param("email") String email, @Param("anio") Integer anio, @Param("mes") Integer mes);
    List<Microemprendimiento> findAllByUsuarioEmailAndDeletedFalse(String email);
}