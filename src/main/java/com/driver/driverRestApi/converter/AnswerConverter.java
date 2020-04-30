package com.driver.driverRestApi.converter;

import com.driver.driverRestApi.dto.request.AnswerRequest;
import com.driver.driverRestApi.dto.response.AnswerResponse;
import com.driver.driverRestApi.model.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter {

    private final ModelMapper mapper = new ModelMapper();


    public AnswerResponse aToResponse(Answer a){
        return mapper.map(a,AnswerResponse.class);
    }

    public Answer reqToAnswer(AnswerRequest aRequest) {
        return mapper.map(aRequest,Answer.class);
    }
}
