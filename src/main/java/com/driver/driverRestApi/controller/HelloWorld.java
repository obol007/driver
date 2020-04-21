package com.driver.driverRestApi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/hello")
public class HelloWorld {

    @GetMapping
    public String hello(){
        return "Hello world";
    }
    @PostMapping("/post")
    public String helloPost(@RequestBody final String hello){
        return hello;
    }
    @PutMapping("/put")
    public String helloPut(@RequestBody final String hello){
        return hello;
    }


}
