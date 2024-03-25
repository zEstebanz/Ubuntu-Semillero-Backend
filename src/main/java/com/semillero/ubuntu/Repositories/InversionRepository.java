package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InversionRepository extends JpaRepository <Inversion, Long> {
    @Query(
            value = "SELECT * FROM inversion WHERE usuario_id = :id",
            nativeQuery = true
    )
    List<Inversion> buscarPorUsuarioId(@Param("id") Long id);
}
