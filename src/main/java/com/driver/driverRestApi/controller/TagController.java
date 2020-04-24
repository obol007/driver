package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.converter.tag.TagConverter;
import com.driver.driverRestApi.dto.TagDto;
import com.driver.driverRestApi.dto.request.TagRequest;
import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.service.impl.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tag")
public class TagController {


    private final TagService tagService;
    private final TagConverter tagConverter;

    public TagController(TagService tagService, TagConverter tagConverter) {
        this.tagService = tagService;
        this.tagConverter = tagConverter;
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Find a tag on id", notes = "Single tag",
            response = Tag.class)
    public ResponseEntity<?> getTagById(@PathVariable("id") Long id){
        Tag tag = tagService.findById(id);
        TagDto tagDto = tagConverter.tagToDto(tag);
        return ResponseEntity.ok(tagDto);
    }

    @PostMapping
    public ResponseEntity<TagDto> createNewTag(@Valid @RequestBody TagRequest tagRequest,
                                               UriComponentsBuilder builder){
        Tag tag = tagService.createNewTag(tagConverter.requestToTag(tagRequest));
        Long id = tag.getId();
        UriComponents uriComponents = builder.path("/api/tag/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(tagConverter.tagToDto(tag),headers, HttpStatus.CREATED);
    }

//    @GetMapping
//    @ResponseBody
//    public List<PostDto> getPosts(...) {
//        //...
//        List<Post> posts = postService.getPostsList(page, size, sortDir, sort);
//        return posts.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public PostDto createPost(@RequestBody PostDto postDto) {
//        Post post = convertToEntity(postDto);
//        Post postCreated = postService.createPost(post));
//        return convertToDto(postCreated);
//    }
//
//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public PostDto getPost(@PathVariable("id") Long id) {
//        return convertToDto(postService.getPostById(id));
//    }


}
