package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.converter.AnswerConverter;
import com.driver.driverRestApi.dto.request.AnswerEditRequest;
import com.driver.driverRestApi.dto.request.AnswerRequest;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Answer;
import com.driver.driverRestApi.model.Question;
import com.driver.driverRestApi.repository.impl.MySqlAnswerRepository;
import com.driver.driverRestApi.repository.impl.MySqlQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerService {

    public AnswerService(MySqlAnswerRepository aRepository,
                         MySqlQuestionRepository qRepository,
                         AnswerConverter aConverter) {
        this.aRepository = aRepository;
        this.qRepository = qRepository;
        this.aConverter = aConverter;
    }

    MySqlAnswerRepository aRepository;
    MySqlQuestionRepository qRepository;
    AnswerConverter aConverter;


    public List<Answer> getAllToQuestion(Long qId) {
        questionExists(qId);
        List<Answer> answers = aRepository.findAllToQuestion(qId);
        if (answers.isEmpty())
            throw new ResourceNotFoundException(String.format("Question with id: '%s' doesn't have any answers yet!", qId));
        return answers;
    }

    public Answer getAnswer(Long qId, Long aId) {
        questionExists(qId);
        return aRepository
                .findById(aId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id: '%s' doesn't have any answers yet!", qId)));
    }

    public void questionExists(Long qId) {
        qRepository.findById(qId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id: '%s' doesn't exist!", qId)));
    }

    public List<Answer> create(AnswerRequest answerRequest, Long qId) {
        questionExists(qId);
        List<Answer> answers = aConverter.reqToAnswer(answerRequest);
        Question q = qRepository.getOne(qId);
        answers.forEach(answer -> {
                    answer.setQuestion(q);
                    aRepository.save(answer);
                });
        return answers;
    }

    public Answer update(AnswerEditRequest answerRequest, Long qId, Long aId) {
        //TODO: check whether the answer is for different question
        questionExists(qId);
        Answer answer = aConverter.editToAnswer(answerRequest);
        Question q = qRepository.getOne(qId);
        answer.setQuestion(q);
        answer.setId(aId);
        return aRepository.save(answer);

    }

    public Boolean doesExist(Long aId) {
        return aRepository.findById(aId).isPresent();
    }

    public void delete(Long qId, Long aId) {
        questionExists(qId);
        //TODO: check whether the answer is for this question
        Answer a = aRepository.findById(aId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Answer with id: '%s' doesn't exist!",aId)));
        aRepository.delete(a);
    }
}
