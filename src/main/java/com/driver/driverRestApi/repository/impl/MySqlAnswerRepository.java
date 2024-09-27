package com.driver.driverRestApi.repository.impl;

import com.driver.driverRestApi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MySqlAnswerRepository extends JpaRepository<Answer,Long> {
    @Query("SELECT a FROM Answer a WHERE a.question.id = ?1")
    List<Answer> findAllToQuestion(Long qId);
}
