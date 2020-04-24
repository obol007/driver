package com.driver.driverRestApi.converter.tag;

import com.driver.driverRestApi.dto.TagDto;
import com.driver.driverRestApi.dto.request.TagRequest;
import com.driver.driverRestApi.model.Tag;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TagConverter {

    ModelMapper mapper = new ModelMapper();


    public TagDto tagToDto(Tag tag){

        return mapper.map(tag,TagDto.class);

    }
    public Tag dtoToTag(TagDto tagDto){
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        return tag;
    }

    public Tag requestToTag(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }
}
