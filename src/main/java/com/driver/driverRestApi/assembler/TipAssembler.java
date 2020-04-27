package com.driver.driverRestApi.assembler;

import com.driver.driverRestApi.controller.TagController;
import com.driver.driverRestApi.controller.TipController;
import com.driver.driverRestApi.dto.request.TagRequest;
import com.driver.driverRestApi.dto.response.TagResponse;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.model.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TipAssembler implements RepresentationModelAssembler<TipResponse, EntityModel<TipResponse>> {

    TagAssembler assembler;

    public TipAssembler(TagAssembler assembler) {
        this.assembler = assembler;
    }

    @Override
    public EntityModel<TipResponse> toModel(TipResponse tipResponse) {


            return new EntityModel<>(tipResponse,
                    linkTo(methodOn(TipController.class).getTip(tipResponse.getId())).withSelfRel(),
                    linkTo(methodOn(TipController.class).getTips()).withRel("tips"));

    }

}
