package com.driver.driverRestApi.dto.response;

import lombok.Data;

@Data
public class AnswerResponse {

    private Long id;
    private Long questionId;
    private String answerText;
    private Boolean isCorrect;

}
