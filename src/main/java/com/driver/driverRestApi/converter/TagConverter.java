package com.driver.driverRestApi.converter;

import com.driver.driverRestApi.dto.response.TagResponse;
import com.driver.driverRestApi.dto.request.TagRequest;
import com.driver.driverRestApi.model.Tag;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TagConverter {

    ModelMapper mapper = new ModelMapper();


    public TagResponse tagToResponse(Tag tag){
        return mapper.map(tag, TagResponse.class);
    }


    public Tag requestToTag(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }
}
