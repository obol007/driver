package com.driver.driverRestApi.converter;

import com.driver.driverRestApi.dto.response.AnswerResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter implements RepresentationModelAssembler<AnswerResponse, EntityModel<AnswerResponse>> {

    @Override
    public EntityModel<AnswerResponse> toModel(AnswerResponse entity) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<AnswerResponse>> toCollectionModel(Iterable<? extends AnswerResponse> entities) {
        return null;
    }
}
