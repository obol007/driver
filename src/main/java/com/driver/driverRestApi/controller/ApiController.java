package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.model.Answer;
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
    @ApiOperation(value = "Show content")
    public RepresentationModel index() {
        RepresentationModel rootModel = new RepresentationModel();
        rootModel.add(linkTo(methodOn(QuestionController.class).getQuestions()).withRel("Questions"));
        rootModel.add(linkTo(methodOn(TagController.class).getTags()).withRel("Tags"));
        rootModel.add(linkTo(methodOn(TipController.class).getTips()).withRel("Tips"));
        return rootModel;
    }
}
