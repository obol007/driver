package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.dto.TagDto;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.repository.TagRepository;
import com.driver.driverRestApi.service.TagServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class TagService implements TagServiceInterface {

    private final TagRepository tagRepository;

    public TagService(@Qualifier("MySQL") TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public Tag findById(Long id) {
    return tagRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(String.format("Tag with id: '%s' doesn't exist",id)));
    }

    public Tag createNewTag(Tag tag) {
        return tagRepository.save(tag);
    }
}
