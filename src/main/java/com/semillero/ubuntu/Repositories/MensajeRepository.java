package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByGestionado(boolean gestionado);
}
