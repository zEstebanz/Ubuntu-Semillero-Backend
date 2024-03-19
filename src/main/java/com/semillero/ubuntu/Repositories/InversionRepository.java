package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InversionRepository extends JpaRepository <Inversion, Long> {
}
