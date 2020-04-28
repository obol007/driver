package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.converter.TagConverter;
import com.driver.driverRestApi.converter.TipConverter;
import com.driver.driverRestApi.dto.request.TagRequest;
import com.driver.driverRestApi.dto.request.TipRequest;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.model.Tip;
import com.driver.driverRestApi.repository.impl.MySqlTagRepository;
import com.driver.driverRestApi.repository.impl.MySqlTipRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    TipConverter tipConverter;
    TagConverter tagConverter;
    MySqlTagRepository tagRepository;
    TagService tagService;
    ModelMapper mapper = new ModelMapper();

    public TipService(MySqlTipRepository tipRepository,
                      TagConverter tagConverter,
                      @Qualifier("MySQL") MySqlTagRepository tagRepository,
                      TagService tagService,
                      TipConverter tipConverter) {
        this.tipRepository = tipRepository;
        this.tagConverter = tagConverter;
        this.tagRepository = tagRepository;
        this.tagService = tagService;
        this.tipConverter = tipConverter;
    }


    public List<Tip> getTips() {
        return tipRepository.findAll();
    }

    public Tip findTip(Long id) {
        return tipRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no tip with id: "+id));
    }

    public Tip createTip(TipRequest tipRequest) {
        Set<TagRequest> tags = tipRequest.getTags();
        //TODO: check if tags already exist
//        Set<Tag> savedTags = tags.stream().map((element)->tagRepository.saveAndFlush(element)).collect(Collectors.toSet());
        Set<Tag> savedTags = tags.stream().map(tagService::createTag).collect(Collectors.toSet());
        Tip tip = tipConverter.requestToTip(tipRequest);
        tip.setTags(savedTags);
       return tipRepository.save(tip);
    }

    public Tip updateTip(TipRequest tipRequest, Long id) {

        Tip tip = tipRepository.findById(id).orElseGet(Tip::new);
        mapper.map(tipRequest,tip);
        tip.setId(id);
        return tipRepository.save(tip);
    }

    public void deleteTip(Long id) {
        Tip tip = tipRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Tip with id: '%s' doesn't exist",id)));
        tipRepository.delete(tip);
    }
}
