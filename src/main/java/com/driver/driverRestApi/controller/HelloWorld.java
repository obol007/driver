package com.driver.driverRestApi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/hello")
public class HelloWorld {

    @ApiOperation(value = "Returns 'Hello world'")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is a message"),
                    @ApiResponse(code=200,message = "That's it"),
                    @ApiResponse(code=401, message = "NO ACCESS")
            }
    )
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
