package com.driver.driverRestApi.dto.request;

import lombok.Data;

@Data
public class AnswerEditRequest {

    private String answerText;
    private Boolean isCorrect;
}
