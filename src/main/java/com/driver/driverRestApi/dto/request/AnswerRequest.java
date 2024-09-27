package com.driver.driverRestApi.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class AnswerRequest {

    private Map<String, Boolean> answerValidation;


}
