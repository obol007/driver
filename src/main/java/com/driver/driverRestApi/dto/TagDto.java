package com.driver.driverRestApi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagDto {

    private Long id;
    @NotNull
    private String name;

}
