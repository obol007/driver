package com.driver.driverRestApi.dto.request;

import com.driver.driverRestApi.model.Tag;
import lombok.Data;


import java.util.Set;

@Data
public class TipRequest {

    private String title;


    private String description;


//    private Set<TagRequest> tags;


}
