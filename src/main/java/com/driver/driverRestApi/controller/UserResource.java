package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserResource {


    @GetMapping
    @ApiOperation(value = "Show all users",
            notes = "All users who are active",
            response = User.class)
    public List<User> getUsers(){
        return Arrays.asList(
                new User("sam", 1000L),
                new User("peter", 2000L)
        );
    }

    @GetMapping("/{userName}")
    public User getUser(@ApiParam(value = "First name of the user",required = true) @PathVariable String userName){
        return new User(userName, 2300L);
    }




}
