package com.driver.driverRestApi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Information about tags")
public class Tag extends BaseEntity {

//     @ApiModelProperty(notes = "Tag name")
     //TODO: unique name validation
     private String name;

}
