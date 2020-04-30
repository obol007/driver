package com.driver.driverRestApi.assembler;

import com.driver.driverRestApi.controller.AnswerController;
import com.driver.driverRestApi.controller.QuestionController;
import com.driver.driverRestApi.dto.response.AnswerResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnswerAssembler implements RepresentationModelAssembler<AnswerResponse, EntityModel<AnswerResponse>> {

    @Override
    public EntityModel<AnswerResponse> toModel(AnswerResponse answer) {

        return new EntityModel<>(answer,
                linkTo(methodOn(AnswerController.class).showAnswer(answer.getQuestionId(),answer.getId())).withSelfRel(),
                linkTo(methodOn(AnswerController.class).showAnswers(answer.getQuestionId())).withRel("All answers to the question with id: "+answer.getQuestionId()));
    }

    public EntityModel<AnswerResponse> toModelWithAllAnswers(AnswerResponse answer) {

        return new EntityModel<>(answer,
                linkTo(methodOn(AnswerController.class).showAnswer(answer.getQuestionId(),answer.getId())).withSelfRel());
    }


    @Override
    public CollectionModel<EntityModel<AnswerResponse>> toCollectionModel(Iterable<? extends AnswerResponse> entities) {
        return null;
    }
}