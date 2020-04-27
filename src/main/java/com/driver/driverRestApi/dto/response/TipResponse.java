package com.driver.driverRestApi.dto.response;

import lombok.Data;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class TipResponse {


    Long id;
    private String title;
    private String description;
    private LocalDateTime created;
    private Set<EntityModel<TagResponse>> tags;

}
