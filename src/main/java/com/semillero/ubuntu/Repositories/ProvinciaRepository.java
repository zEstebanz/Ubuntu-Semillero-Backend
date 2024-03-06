package com.semillero.ubuntu.Repositories;


import com.semillero.ubuntu.Entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    @Query("SELECT p FROM Provincia p WHERE p.pais.id = :idPais")
    List<Provincia> findByIdPais(@Param("idPais") Long idPais);
}
