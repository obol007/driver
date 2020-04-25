package com.driver.driverRestApi.dto.response;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagResponse {

    private Long id;
    private String name;

}
