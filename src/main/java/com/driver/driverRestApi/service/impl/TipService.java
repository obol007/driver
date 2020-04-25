package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.converter.TagConverter;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.model.Tip;
import com.driver.driverRestApi.repository.impl.MySqlTagRepository;
import com.driver.driverRestApi.repository.impl.MySqlTipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TipService {

    MySqlTipRepository tipRepository;
    TagConverter tagConverter;
    MySqlTagRepository tagRepository;

    public TipService(MySqlTipRepository tipRepository,
                      TagConverter tagConverter,
                      @Qualifier("MySQL") MySqlTagRepository tagRepository) {
        this.tipRepository = tipRepository;
        this.tagConverter = tagConverter;
        this.tagRepository = tagRepository;
    }


    public List<Tip> getTips() {
        return tipRepository.findAll();
    }

    public Tip findTip(Long id) {
        return tipRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no tip with id: "+id));
    }

    public Tip createTip(Tip tip) {
        Set<Tag> tags = tip.getTags();
        //TODO: check if tags already exist
        Set<Tag> savedTags = tags.stream().map((element)->tagRepository.saveAndFlush(element)).collect(Collectors.toSet());
        tip.setTags(savedTags);
       return tipRepository.save(tip);
    }
}
