package com.driver.driverRestApi.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class TagRequest {
    @ApiModelProperty(notes = "Tag name")
    private String name;
}
