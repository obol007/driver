package com.driver.driverRestApi.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public RepresentationModel index() {
        RepresentationModel rootModel = new RepresentationModel();
        rootModel.add(linkTo(methodOn(TipController.class).getTips()).withRel("tips"));
        rootModel.add(linkTo(methodOn(TagController.class).getTags()).withRel("tags"));
        return rootModel;
    }
}
