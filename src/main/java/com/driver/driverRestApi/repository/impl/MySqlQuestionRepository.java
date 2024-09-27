package com.driver.driverRestApi.repository.impl;

import com.driver.driverRestApi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MySqlQuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.tip.id=?1")
    List<Question> findAllByTipId(Long id);
}
