package com.driver.driverRestApi.dto.response;


import com.driver.driverRestApi.dto.request.TagRequest;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class TipResponse {

    Long id;

    private String title;


    private String description;
    private LocalDateTime created;

    private Set<TagRequest> tags;

}
