package com.driver.driverRestApi.assembler;

import com.driver.driverRestApi.controller.TagController;
import com.driver.driverRestApi.dto.response.TagResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

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

}
