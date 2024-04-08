package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.GestionInversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GestionInversionRepository extends JpaRepository <GestionInversion, Long> {
    @Query(
            value = "SELECT * FROM gestion_inversion WHERE micro_id = :id",
            nativeQuery = true
    )
    Optional<GestionInversion> buscarPorMicroId(@Param("id") Long id);
}
