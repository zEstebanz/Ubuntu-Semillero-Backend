package com.semillero.ubuntu.ChatBot.Repositories;

import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

   @Query("SELECT q FROM Question q WHERE q.active=false")
    List<Question> getQuestionsNotActive();



    @Modifying
    @Query("UPDATE Question q  SET q.active = false WHERE q.id = :id")
    void setToNotActive(@Param("id") Long id);

}


