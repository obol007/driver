package com.driver.driverRestApi.converter;

import com.driver.driverRestApi.dto.request.QuestionRequest;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import com.driver.driverRestApi.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {

    private final ModelMapper mapper = new ModelMapper();


    public QuestionResponse qToResponse(Question q){
        return mapper.map(q,QuestionResponse.class);
    }


    public Question reqToQuestion(QuestionRequest qRequest) {
        return mapper.map(qRequest,Question.class);
    }
}
