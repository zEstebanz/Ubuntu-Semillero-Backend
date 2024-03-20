package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Microemprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroemprendimientoRepository extends JpaRepository<Microemprendimiento, Long> {
    @Query("SELECT m FROM Microemprendimiento m WHERE LOWER(m.nombre) LIKE LOWER(concat('%', :nombre, '%'))")
    List<Microemprendimiento> findByNameMicroemprendimiento(@Param("nombre") String nombre);
}