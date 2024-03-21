package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Microemprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MicroemprendimientoRepository extends JpaRepository<Microemprendimiento, Long> {
    @Query("SELECT m FROM Microemprendimiento m WHERE LOWER(m.nombre) LIKE LOWER(concat('%', :nombre, '%')) AND m.deleted = false")
    List<Microemprendimiento> findByNameMicroemprendimiento(@Param("nombre") String nombre);
    @Query("SELECT m FROM Microemprendimiento m WHERE m.rubro.id = :idRubro AND m.deleted = false")
    List<Microemprendimiento> findByRubro(@Param("idRubro") Long idRubro);
    Optional<Microemprendimiento> findByIdAndDeletedFalse(Long id);
    @Query(value = "SELECT " +
            "COUNT(*) AS totalMicroemprendimientos," +
            "COUNT(CASE WHEN gestionado = true THEN 1 END) AS gestionados," +
            "COUNT(CASE WHEN gestionado = false THEN 1 END) AS noGestionados " +
            "FROM microemprendimiento " +
            "WHERE deleted = false;", nativeQuery = true)
    List<Object[]> estadisticas();
}