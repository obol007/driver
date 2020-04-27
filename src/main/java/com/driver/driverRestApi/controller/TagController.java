package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.TagAssembler;
import com.driver.driverRestApi.converter.TagConverter;
import com.driver.driverRestApi.dto.response.TagResponse;
import com.driver.driverRestApi.dto.request.TagRequest;
import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.service.impl.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/tag")
public class TagController {


    private final TagService tagService;
    private final TagConverter tagConverter;
    private final TagAssembler assembler;

    public TagController(TagService tagService, TagConverter tagConverter, TagAssembler assembler) {
        this.tagService = tagService;
        this.tagConverter = tagConverter;
        this.assembler = assembler;
    }

    @GetMapping
    @ApiOperation(value = "Find all tags")
    public CollectionModel<EntityModel<TagResponse>> getTags() {
        List<Tag> tags = tagService.getAllTags();
        List<EntityModel<TagResponse>> tagEntities = tags.stream()
//              .map(tag -> tagConverter.tagToResponse(tag))
                .map(tagConverter::tagToResponse)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(tagEntities,
                linkTo(methodOn(TagController.class).getTags()).withSelfRel());
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Find a single tag", notes = "Single tag",
            response = Tag.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "You have found it!")
            }
    )
    public EntityModel<TagResponse> getTag(@PathVariable("id") Long id) {
        Tag tag = tagService.findById(id);
        TagResponse tagResponse = tagConverter.tagToResponse(tag);
        return assembler.toModel(tagResponse);
    }


    @PostMapping
    @ApiOperation(value = "Create a tag")
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest tagRequest,
                                                 UriComponentsBuilder builder) {
        Tag tag = tagConverter.requestToTag(tagRequest);
        Tag tagCreated = tagService.createTag(tag);

        Long id = tagCreated.getId();
        UriComponents uriComponents = builder.path("/api/tag/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(tagConverter.tagToResponse(tagCreated), headers, HttpStatus.CREATED);

    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Update a tag")
    public TagResponse updateTag(@RequestBody TagRequest tagRequest,
                                 @PathVariable Long id) {
        Tag tag = tagService.updateTag(tagRequest, id);
        return tagConverter.tagToResponse(tag);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a tag")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }


}
