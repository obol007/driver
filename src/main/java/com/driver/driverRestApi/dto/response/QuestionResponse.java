package com.driver.driverRestApi.dto.response;

import lombok.Data;

@Data
public class QuestionResponse {

    private Long id;
    private Long tipId;
    private String questionText;

}
