package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.converter.QuestionConverter;
import com.driver.driverRestApi.dto.request.QuestionRequest;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Question;
import com.driver.driverRestApi.repository.impl.MySqlQuestionRepository;
import com.driver.driverRestApi.repository.impl.MySqlTipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class QuestionService {

    MySqlQuestionRepository questionRepository;
    MySqlTipRepository tipRepository;
    QuestionConverter qConverter;

    public QuestionService(MySqlQuestionRepository questionRepository,
                           MySqlTipRepository tipRepository,
                           QuestionConverter qConverter) {
        this.questionRepository = questionRepository;
        this.tipRepository = tipRepository;
        this.qConverter = qConverter;
    }


    public List<Question> allByTipId(Long id) {
        tipExists(id);
        List<Question> questions = questionRepository.findAllByTipId(id);
        if (questions.isEmpty())
            throw new ResourceNotFoundException("There are no questions for the tip with id: " + id);
        return questions;
    }

    public Question oneById(Long qId, Long tipId) {
        tipExists(tipId);
        return questionRepository.findById(qId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Question with id: '%s' for tip with id: '%s' doesn't exist", qId,tipId)));
    }

    public void tipExists(Long id) {
        if (!tipRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Tip with id: '%s' doesn't exist", id));
        }
    }

    public Question add(QuestionRequest qRequest, Long tipId) {
        tipExists(tipId);
        Question q = qConverter.reqToQuestion(qRequest);
        q.setTip(tipRepository.getOne(tipId));
        return questionRepository.save(q);

    }

    public Question update(QuestionRequest qRequest, Long tipId, Long qId) {
        tipExists(tipId);
        Question q = qConverter.reqToQuestion(qRequest);
        q.setTip(tipRepository.getOne(tipId));
        q.setId(qId);
        return questionRepository.save(q);
    }

    public Boolean doesExist(Long qId) {
        return questionRepository.findById(qId).isPresent();
    }

    public void delete(Long qId, Long tipId) {
        tipExists(tipId);
        questionRepository.findById(qId)
                .orElseThrow(()->new ResourceNotFoundException(String.format("Question with id: '%s' doesn't exist!",qId)));
        questionRepository.deleteById(qId);
    }
}
