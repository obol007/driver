package com.driver.driverRestApi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ApiModel(description = "Information about the user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty(notes = "First name")
    private String userName;
    private Long salary;

    public User(String userName, Long salary) {
        this.userName = userName;
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}

