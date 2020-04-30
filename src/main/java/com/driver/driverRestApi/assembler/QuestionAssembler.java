package com.driver.driverRestApi.assembler;

import com.driver.driverRestApi.dto.response.QuestionResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class QuestionAssembler implements RepresentationModelAssembler<QuestionResponse, EntityModel<QuestionResponse>> {

    @Override
    public EntityModel<QuestionResponse> toModel(QuestionResponse entity) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<QuestionResponse>> toCollectionModel(Iterable<? extends QuestionResponse> entities) {
        return null;
    }
}
