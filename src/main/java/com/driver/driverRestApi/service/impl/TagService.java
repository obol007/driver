package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.dto.request.TagRequest;
import com.driver.driverRestApi.dto.response.TagResponse;
import com.driver.driverRestApi.exception.EmptyTagException;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.repository.TagRepository;
import com.driver.driverRestApi.service.TagServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional
@Slf4j
public class TagService implements TagServiceInterface {

    private final TagRepository tagRepository;
    private final ModelMapper mapper = new ModelMapper();

    public TagService(@Qualifier("MySQL") TagRepository tagRepository) {
        this.tagRepository = tagRepository;

    }


    public Tag findById(Long id) {
    return tagRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(String.format("Tag with id: '%s' doesn't exist",id)));
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag updateTag(TagRequest tagRequest, Long id) {

        if(tagRequest.getName().equals("")) throw new EmptyTagException("Tag name cannot be empty");

        Tag tag = tagRepository.findById(id).orElseGet(Tag::new);
        mapper.map(tagRequest,tag);
        tag.setId(id);
        return tagRepository.save(tag);

    }

    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Tag with id: '%s' doesn't exist",id)));
        tagRepository.delete(tag);
    }
}
