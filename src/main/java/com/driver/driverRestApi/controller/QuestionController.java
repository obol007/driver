package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.QuestionAssembler;
import com.driver.driverRestApi.converter.QuestionConverter;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import com.driver.driverRestApi.model.Question;
import com.driver.driverRestApi.service.impl.QuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/tip/{tipId}/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final QuestionAssembler assembler;

    public QuestionController(QuestionService questionService,
                              QuestionConverter questionConverter,
                              QuestionAssembler assembler) {
        this.questionService = questionService;
        this.questionConverter = questionConverter;
        this.assembler = assembler;
    }


    @GetMapping
    @ApiOperation(value = "Show all question for a given tip ")
    public CollectionModel<?> getQuestions(@PathVariable ("tipId") Long id){
        List<Question> questions = questionService.allByTipId(id);
        List<EntityModel<QuestionResponse>> questionEntities = questions.stream()
                .map(questionConverter::toResponse)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(questionEntities,
                linkTo(methodOn(QuestionController.class).getQuestions(id)).withSelfRel());

    }

}
