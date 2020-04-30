package com.driver.driverRestApi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data

@ApiModel(description = "Information about tags")
public class Tag extends BaseEntity {

//     @ApiModelProperty(notes = "Tag name")
     private String name;

     @Override
     public boolean equals(Object o) {

          // If the object is compared with itself then return true
          if (o == this) {
               return true;
          }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
          if (!(o instanceof Tag)) {
               return false;
          }

          // typecast o to Complex so that we can compare data members
          Tag tag = (Tag) o;

          // Compare the data members and return accordingly
          return tag.getName().equals(name);

     }







}
