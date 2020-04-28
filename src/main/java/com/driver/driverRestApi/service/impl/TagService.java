package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.converter.TagConverter;
import com.driver.driverRestApi.dto.request.TagRequest;
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
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class TagService implements TagServiceInterface {

    private final TagRepository tagRepository;
    private final TagConverter tagConverter;
    private final ModelMapper mapper = new ModelMapper();

    public TagService(@Qualifier("MySQL") TagRepository tagRepository,
                      TagConverter tagConverter) {
        this.tagRepository = tagRepository;
        this.tagConverter = tagConverter;
    }


    public Tag findById(Long id) {
    return tagRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(String.format("Tag with id: '%s' doesn't exist",id)));
    }

    public Tag createTag(TagRequest tagRequest) {
        Optional<Tag> tag = tagRepository.findByNameIgnoreCase(tagRequest.getName());
        if(tag.isEmpty()){
            Tag newTag = tagConverter.requestToTag(tagRequest);
            return tagRepository.save(newTag);
        }
        return tag.get();
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
        //TODO: check if tag is in use!
        Tag tag = tagRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Tag with id: '%s' doesn't exist",id)));
        tagRepository.delete(tag);
    }
}
