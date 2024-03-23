package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long> {
    @Query(value = "SELECT r.*, COUNT(CASE WHEN m.email = :email THEN 1 ELSE NULL END) AS cantMicroemprendimientos " +
            "FROM rubro r " +
            "LEFT JOIN microemprendimiento m ON r.id = m.id_rubro AND m.deleted = false " +
            "GROUP BY r.id " +
            "ORDER BY cantMicroemprendimientos DESC", nativeQuery = true)
    List<Object[]> estadisticasPorUsuario(@Param("email") String email);
}
