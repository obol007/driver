package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Question;
import com.driver.driverRestApi.model.Tip;
import com.driver.driverRestApi.repository.impl.MySqlQuestionRepository;
import com.driver.driverRestApi.repository.impl.MySqlTipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class QuestionService {

    MySqlQuestionRepository questionRepository;
    MySqlTipRepository tipRepository;

    public QuestionService(MySqlQuestionRepository questionRepository,
                           MySqlTipRepository tipRepository) {
        this.questionRepository = questionRepository;
        this.tipRepository = tipRepository;
    }



    public List<Question> allByTipId(Long id) {
        Optional<Tip> tip = tipRepository.findById(id);
        if(tip.isEmpty()) throw new ResourceNotFoundException(String.format("Tip with id: '%s' doesn't exist",id));
        List<Question> questions = questionRepository.findAllByTipId(id);
        if(questions.isEmpty()) throw new ResourceNotFoundException("There are no questions for tip with id: "+id);
        return questions;
    }
}
