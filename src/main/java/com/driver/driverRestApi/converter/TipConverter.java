package com.driver.driverRestApi.converter;

import com.driver.driverRestApi.dto.request.TipRequest;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.model.Tip;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TipConverter {

    ModelMapper mapper = new ModelMapper();

    TagConverter tagConverter;

    public TipConverter(TagConverter tagConverter) {
        this.tagConverter = tagConverter;
    }

    public TipResponse tipToResponse(Tip tip){
        return mapper.map(tip,TipResponse.class);
    }


    public Tip requestToTip(TipRequest tipRequest) {
        return mapper.map(tipRequest,Tip.class);
    }
}
