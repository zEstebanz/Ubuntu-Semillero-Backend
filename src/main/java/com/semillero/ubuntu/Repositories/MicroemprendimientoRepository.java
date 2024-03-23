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
    @Query("SELECT m FROM Microemprendimiento m WHERE LOWER(m.nombre) LIKE LOWER(concat('%', :nombre, '%')) AND m.deleted = false")
    List<Microemprendimiento> findByNameMicroemprendimiento(@Param("nombre") String nombre);
    List<Microemprendimiento> findByRubroAndDeletedFalse(Rubro rubro);
    Optional<Microemprendimiento> findByIdAndDeletedFalse(Long id);
    @Query(value = "SELECT " +
            "COUNT(*) AS totalMicroemprendimientos," +
            "COUNT(CASE WHEN gestionado = true THEN 1 END) AS gestionados," +
            "COUNT(CASE WHEN gestionado = false THEN 1 END) AS noGestionados " +
            "FROM microemprendimiento " +
            "WHERE deleted = false " +
            "AND id_usuario = ?1", nativeQuery = true)
    List<Object[]> estadisticas(@Param("idUsuario") Long idUsuario);

    List<Microemprendimiento> findAllByUsuarioIdAndDeletedFalse(Long idUsuario);

    @Query("SELECT COUNT(m) FROM Microemprendimiento m WHERE m.fechaCreacion >= :fechaInicio AND m.fechaCreacion <= :fechaFin")
    long countByFechaCreacionBetween(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
    @Query("SELECT COUNT(m) AS totalMicroemprendimientos, " +
            "COUNT(CASE WHEN m.gestionado = true THEN 1 END) AS gestionados, " +
            "COUNT(CASE WHEN m.gestionado = false THEN 1 END) AS noGestionados " +
            "FROM Microemprendimiento m " +
            "WHERE m.deleted = false " +
            "AND m.usuario.email = :email")
    List<Object[]> estadisticas(@Param("email") String email);
    List<Microemprendimiento> findAllByUsuarioEmailAndDeletedFalse(String email);
}