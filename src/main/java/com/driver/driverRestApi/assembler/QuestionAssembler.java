package com.driver.driverRestApi.assembler;

import com.driver.driverRestApi.controller.AnswerController;
import com.driver.driverRestApi.controller.QuestionController;
import com.driver.driverRestApi.controller.TagController;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class QuestionAssembler implements RepresentationModelAssembler<QuestionResponse, EntityModel<QuestionResponse>> {

    @Override
    public EntityModel<QuestionResponse> toModel(QuestionResponse questionResponse)
    {
        return new EntityModel<>(questionResponse,
                linkTo(methodOn(QuestionController.class).getQuestion(questionResponse.getId(),questionResponse.getTipId())).withSelfRel(),
                linkTo(methodOn(QuestionController.class).getQuestions(questionResponse.getTipId()))
                        .withRel("All questions of the tip no."+questionResponse.getTipId()),
                linkTo(methodOn(AnswerController.class).allQuestions()).withRel("All questions"));
    }

    public EntityModel<QuestionResponse> toModelWithAllQuestion(QuestionResponse questionResponse)
    {
        return new EntityModel<>(questionResponse,
                linkTo(methodOn(QuestionController.class).getQuestion(questionResponse.getId(),questionResponse.getTipId())).withSelfRel(),
                linkTo(methodOn(QuestionController.class).getQuestions(questionResponse.getTipId()))
                        .withRel("All questions of the tip no."+questionResponse.getTipId()));
    }


    @Override
    public CollectionModel<EntityModel<QuestionResponse>> toCollectionModel(Iterable<? extends QuestionResponse> entities) {

        return null;
    }

//    public class AlbumModelAssembler
//            extends RepresentationModelAssemblerSupport<AlbumEntity, AlbumModel>
//    {
//        @Override
//        public CollectionModel<AlbumModel> toCollectionModel(Iterable<? extends AlbumEntity> entities)
//        {
//            CollectionModel<AlbumModel> actorModels = super.toCollectionModel(entities);
//        }
//    }
}
