package com.driver.driverRestApi.converter;

import com.driver.driverRestApi.assembler.TagAssembler;
import com.driver.driverRestApi.dto.request.TipRequest;
import com.driver.driverRestApi.dto.response.TagResponse;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.model.Tip;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class TipConverter {

    ModelMapper mapper = new ModelMapper();

    TagConverter tagConverter;
    TagAssembler assembler;

    public TipConverter(TagConverter tagConverter, TagAssembler assembler) {
        this.tagConverter = tagConverter;
        this.assembler = assembler;
    }

    public TipResponse tipToResponse(Tip tip){
        TipResponse tipResponse = new TipResponse();
        Set<TagResponse> tagResponses = tip.getTags().stream().map(tagConverter::tagToResponse).collect(Collectors.toSet());
        tipResponse.setTags(tagResponses.stream().map(assembler::toModelWithSelfRel).collect(Collectors.toSet()));
        tipResponse.setCreated(tip.getCreated());
        tipResponse.setUpdated(tip.getUpdated());
        tipResponse.setDescription(tip.getDescription());
        tipResponse.setTitle(tip.getTitle());
        tipResponse.setId(tip.getId());
        return tipResponse;
//        return mapper.map(tip,TipResponse.class);
    }


    public Tip requestToTip(TipRequest tipRequest) {
        return mapper.map(tipRequest,Tip.class);
    }
}
