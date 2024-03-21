package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long> {
    @Query(value = "SELECT r.*, COUNT(m.id) AS cantEmprendimientos FROM rubro r " +
            "LEFT JOIN microemprendimiento m ON r.id = m.id_rubro AND m.deleted = false " +
            "GROUP BY r.id ORDER BY cantEmprendimientos DESC", nativeQuery = true)
    List<Object[]> getRubrosOrderByCantMicroemprendimientos();
}
