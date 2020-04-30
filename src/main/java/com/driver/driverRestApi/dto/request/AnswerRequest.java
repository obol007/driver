package com.driver.driverRestApi.dto.request;

import lombok.Data;

@Data
public class AnswerRequest {

    private Long questionId;
    private String answerText;
    private Boolean isCorrect;

}
