package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Microemprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroemprendimientoRepository extends JpaRepository<Microemprendimiento, Long> {
}