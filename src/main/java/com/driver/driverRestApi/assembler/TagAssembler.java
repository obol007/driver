package com.driver.driverRestApi.assembler;

import com.driver.driverRestApi.controller.TagController;
import com.driver.driverRestApi.dto.response.TagResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagAssembler implements RepresentationModelAssembler<TagResponse, EntityModel<TagResponse>> {



    @Override
    public EntityModel<TagResponse> toModel(TagResponse tagResponse) {
        return new EntityModel<>(tagResponse,
        linkTo(methodOn(TagController.class).getTag(tagResponse.getId())).withSelfRel(),
        linkTo(methodOn(TagController.class).getTags()).withRel("tags"));
    }

    @Override
    public CollectionModel<EntityModel<TagResponse>> toCollectionModel(Iterable<? extends TagResponse> tags) {

        return null;

//        return new CollectionModel<>(tags,
//                linkTo(methodOn(TagController.class).getTags()).withSelfRel());

    }
}
