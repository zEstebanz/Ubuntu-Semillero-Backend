package com.semillero.ubuntu.ChatBot.Repositories;

import com.semillero.ubuntu.ChatBot.Entities.Pregunta;
import com.semillero.ubuntu.ChatBot.Entities.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    @Query(value = "SELECT * FROM pregunta WHERE inicial = true", nativeQuery = true)
    List<Pregunta> getInicial();
}
