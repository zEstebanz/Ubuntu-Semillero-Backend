package com.semillero.ubuntu.ChatBot.Repositories;

import com.semillero.ubuntu.ChatBot.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

   @Query("SELECT q FROM Question q WHERE q.active = false")
   List<Question> getQuestionsNotActive();

   @Query("SELECT q FROM Question q WHERE q.type = 'INITIAL' AND q.active = true")
   List<Question> getActiveInitialQuestions();

}


