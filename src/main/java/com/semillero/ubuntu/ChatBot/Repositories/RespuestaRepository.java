package com.semillero.ubuntu.ChatBot.Repositories;

import com.semillero.ubuntu.ChatBot.Entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Answer, Long> {

}
