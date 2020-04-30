package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Answer;
import com.driver.driverRestApi.repository.impl.MySqlAnswerRepository;
import com.driver.driverRestApi.repository.impl.MySqlQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerService {
    MySqlAnswerRepository aRepository;
    MySqlQuestionRepository qRepository;

    public AnswerService(MySqlAnswerRepository aRepository, MySqlQuestionRepository qRepository) {
        this.aRepository = aRepository;
        this.qRepository = qRepository;
    }

    public List<Answer> getAllToQuestion(Long qId) {
       questionExists(qId);
        List<Answer> answers = aRepository.findAllToQuestion(qId);
        if(answers.isEmpty()) throw new ResourceNotFoundException(String.format("Question with id: '%s' doesn't have any answers yet!",qId));
        return answers;
    }

    public Answer getAnswer(Long qId, Long aId) {
        questionExists(qId);
        return aRepository
                .findById(aId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Question with id: '%s' doesn't have any answers yet!",qId)));
    }

    public void questionExists(Long qId){
        qRepository.findById(qId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Question with id: '%s' doesn't exist!",qId)));
    }
}
