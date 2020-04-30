package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.AnswerAssembler;
import com.driver.driverRestApi.assembler.QuestionAssembler;
import com.driver.driverRestApi.converter.AnswerConverter;
import com.driver.driverRestApi.converter.QuestionConverter;
import com.driver.driverRestApi.dto.response.AnswerResponse;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import com.driver.driverRestApi.model.Answer;
import com.driver.driverRestApi.model.Question;
import com.driver.driverRestApi.service.impl.AnswerService;
import com.driver.driverRestApi.service.impl.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequestMapping("/api/question")
public class AnswerController {

    QuestionService qService;
    AnswerService aService;
    QuestionConverter qConverter;
    AnswerConverter aConverter;
    QuestionAssembler qAssembler;
    AnswerAssembler aAssembler;

    public AnswerController(QuestionService qService,
                            AnswerService aService,
                            QuestionConverter qConverter,
                            QuestionAssembler qAssembler,
                            AnswerConverter aConverter,
                            AnswerAssembler aAssembler) {
        this.qService = qService;
        this.aService = aService;
        this.qConverter = qConverter;
        this.aConverter = aConverter;
        this.qAssembler = qAssembler;
        this.aAssembler = aAssembler;
    }

    @GetMapping
    @ApiOperation(value = "Show all questions")
    public CollectionModel<?> getQuestions() {
        List<Question> questions = qService.getAll();
        List<EntityModel<QuestionResponse>> qEntities = questions.stream()
                .map(qConverter::qToResponse)
                .map(qAssembler::toModelWithAllQuestion)
                .collect(Collectors.toList());
        return new CollectionModel<>(qEntities,
                linkTo(methodOn(AnswerController.class).getQuestions()).withSelfRel());
    }


    @GetMapping("/{questionId}/answer")
    @ApiOperation(value = "Show all answers for a given question")
    public CollectionModel<?> showAnswers(@PathVariable("questionId") Long qId) {
        List<Answer> answers = aService.getAllToQuestion(qId);
        List<EntityModel<AnswerResponse>> aEntities = answers.stream()
                .map(aConverter::aToResponse)
                .map(aAssembler::toModelWithAllAnswers)
                .collect(Collectors.toList());
        return new CollectionModel<>(aEntities,
        linkTo(methodOn(AnswerController.class).showAnswers(qId)).withSelfRel());
    }
    @GetMapping("/{questionId}/answer/{answerId}")
    @ApiOperation(value = "Show a single answer to the given question")
    public ResponseEntity<?> showAnswer(@PathVariable("questionId") Long qId, @PathVariable("answerId") Long aId){
        Answer a = aService.getAnswer(qId,aId);
        EntityModel<AnswerResponse> aEntity = aAssembler.toModel(aConverter.aToResponse(a));
        return ResponseEntity.ok().body(aEntity);
    }

}
