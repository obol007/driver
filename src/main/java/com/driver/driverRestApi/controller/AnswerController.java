package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.AnswerAssembler;
import com.driver.driverRestApi.assembler.QuestionAssembler;
import com.driver.driverRestApi.converter.AnswerConverter;
import com.driver.driverRestApi.converter.QuestionConverter;
import com.driver.driverRestApi.dto.request.AnswerEditRequest;
import com.driver.driverRestApi.dto.request.AnswerRequest;
import com.driver.driverRestApi.dto.response.AnswerResponse;
import com.driver.driverRestApi.model.Answer;
import com.driver.driverRestApi.service.impl.AnswerService;
import com.driver.driverRestApi.service.impl.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping("/{questionId}/answer")
    @ApiOperation(value = "Show all answers to a given question")
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
    @ApiOperation(value = "Show a single answer to a given question")
    public ResponseEntity<?> showAnswer(@PathVariable("questionId") Long qId, @PathVariable("answerId") Long aId) {
        Answer a = aService.getAnswer(qId, aId);
        EntityModel<AnswerResponse> aEntity = aAssembler.toModel(aConverter.aToResponse(a));
        return ResponseEntity.ok().body(aEntity);
    }

    @PostMapping("/{questionId}/answer")
    @ApiOperation(value = "Create an answer/answers to a given question")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionModel<?> createAnswer(@PathVariable("questionId") Long qId,
                                           @Valid @RequestBody AnswerRequest answerRequest) {
        List<Answer> answers = aService.create(answerRequest, qId);
        List<EntityModel<AnswerResponse>> aEntities = answers.stream()
                .map(aConverter::aToResponse)
                .map(aAssembler::toModelWithAllAnswers)
                .collect(Collectors.toList());
        return new CollectionModel<>(aEntities,
                linkTo(methodOn(AnswerController.class).showAnswers(qId)).withSelfRel());
    }

    @PutMapping("/{questionId}/answer/{answerId}")
    @ApiOperation(value = "Edit an answer to a given question")
    public ResponseEntity<?> editAnswer(@PathVariable("questionId") Long qId,
                                        @PathVariable("answerId") Long aId,
                                        @Valid @RequestBody AnswerEditRequest answerRequest) {
        Boolean aExists = aService.answerExists(aId);
        Answer a = aService.update(answerRequest, qId, aId);
        EntityModel<AnswerResponse> aEntity = aAssembler.toModel(aConverter.aToResponse(a));
        if (aExists) {
            return ResponseEntity.ok().body(aEntity);
        }
        return ResponseEntity
                .created(aEntity.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(aEntity);
    }

    @DeleteMapping("/{questionId}/answer/{answerId}")
    @ApiOperation(value = "Delete an answer to a given question")
    public ResponseEntity<?> deleteAnswer(@PathVariable("questionId") Long qId,
                                          @PathVariable("answerId") Long aId){
        aService.delete(qId,aId);
//        return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(String.format("Answer with id: '%s' has been deleted!",aId));
    }


}
