package com.driver.driverRestApi.converter;

import com.driver.driverRestApi.dto.request.AnswerEditRequest;
import com.driver.driverRestApi.dto.request.AnswerRequest;
import com.driver.driverRestApi.dto.response.AnswerResponse;
import com.driver.driverRestApi.model.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AnswerConverter {

    private final ModelMapper mapper = new ModelMapper();


    public AnswerResponse aToResponse(Answer a){
        return mapper.map(a,AnswerResponse.class);
    }

    public List<Answer> reqToAnswer(AnswerRequest aRequest) {
        List<Answer> answers = new ArrayList<>();
        Map<String, Boolean> questionValidation = aRequest.getAnswerValidation();
        questionValidation.forEach((question,validation)-> answers.add(new Answer(question,validation)));
        return answers;
    }

    public Answer editToAnswer(AnswerEditRequest request){
        return mapper.map(request,Answer.class);
    }


}
