package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.converter.QuestionConverter;
import com.driver.driverRestApi.dto.request.QuestionRequest;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import com.driver.driverRestApi.exception.ForbiddenEditingException;
import com.driver.driverRestApi.exception.NoContentException;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.exception.advice.NoContent;
import com.driver.driverRestApi.model.Question;
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
            throw new NoContentException("There are no questions for the tip with id: " + id);
        return questions;
    }

    public Question oneById(Long qId, Long tipId) {
        tipExists(tipId);
        return questionRepository.findById(qId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String
                                .format("Question with id: '%s' for tip with id: '%s' doesn't exist", qId,tipId)));
    }

    public void tipExists(Long id) {
        if (!tipRepository.existsById(id)) {
            throw new ResourceNotFoundException(String
                    .format("Tip with id: '%s' doesn't exist", id));
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
        tipQuestionAgreement(tipId,qId);
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
        tipQuestionAgreement(tipId,qId);
        questionRepository.deleteById(qId);
    }

    private void tipQuestionAgreement(Long tipId, Long qId) {
        Optional<Question> q = questionRepository.findById(qId);
        if(q.isEmpty()){
            throw new ResourceNotFoundException(String
                    .format("Question with id: '%s' doesn't exist!",qId));
        }
        if(!tipId.equals(q.get().getTip().getId())){
            throw new ForbiddenEditingException(String
                    .format("Question with id: '%s' doesn't belong to the tip with id: '%s'", qId, tipId));
        }
    }

    public List<Question> getAll() {
        List<Question> questions = questionRepository.findAll();
        if(questions.isEmpty()) throw new NoContentException("There are no questions in the database");
        return questions;
    }
}
