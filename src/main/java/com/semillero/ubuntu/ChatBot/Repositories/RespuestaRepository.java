package com.semillero.ubuntu.ChatBot.Repositories;

import com.semillero.ubuntu.ChatBot.Entities.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

}
