package com.driver.driverRestApi.repository.impl;

import com.driver.driverRestApi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlAnswerRepository extends JpaRepository<Answer,Long> {
}
