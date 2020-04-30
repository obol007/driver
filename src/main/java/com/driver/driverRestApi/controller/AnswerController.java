package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.QuestionAssembler;
import com.driver.driverRestApi.converter.QuestionConverter;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import com.driver.driverRestApi.model.Question;
import com.driver.driverRestApi.service.impl.AnswerService;
import com.driver.driverRestApi.service.impl.QuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/question")
public class AnswerController {

    QuestionService qService;
    AnswerService aService;
    QuestionConverter qConverter;
    QuestionAssembler assembler;

    public AnswerController(QuestionService qService,
                            AnswerService aService,
                            QuestionConverter qConverter,
                            QuestionAssembler assembler) {
        this.qService = qService;
        this.aService = aService;
        this.qConverter = qConverter;
        this.assembler = assembler;
    }

    @GetMapping
    @ApiOperation(value = "Show all questions")
    public CollectionModel<?> allQuestions(){
        List<Question> questions = qService.getAll();
        List<EntityModel<QuestionResponse>> qEntities = questions.stream()
                .map(qConverter::qToResponse)
                .map(assembler::toModelWithAllQuestion)
                .collect(Collectors.toList());
        //TODO: implement the method in the assembler
        return new CollectionModel<>(qEntities,
                linkTo(methodOn(AnswerController.class).allQuestions()).withSelfRel());
    }

}
