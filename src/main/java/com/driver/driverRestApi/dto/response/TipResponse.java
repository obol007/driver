package com.driver.driverRestApi.dto.response;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Data
public class TipResponse {

    Long id;

    private String title;


    private String description;
    private LocalDateTime created;




//    private Set<TagResponse> tags;

}
