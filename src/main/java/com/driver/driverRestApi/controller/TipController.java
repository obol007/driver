package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.converter.TipConverter;
import com.driver.driverRestApi.dto.request.TipRequest;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.model.Tip;
import com.driver.driverRestApi.service.impl.TipService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tip")
public class TipController {

    TipService tipService;
    TipConverter tipConverter;

    public TipController(TipService tipService,
                         TipConverter tipConverter) {
        this.tipService = tipService;
        this.tipConverter = tipConverter;
    }

    @GetMapping
    @ApiOperation(value = "Find all tips")
    public List<TipResponse> getTips(){
        return tipService.getTips().stream()
                .map(tipConverter::tipToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a single tip")
    public TipResponse getTip(@PathVariable Long id){
       Tip tip = tipService.findTip(id);
       return tipConverter.tipToResponse(tip);
    }

    @PostMapping
    @ApiOperation(value = "Create a tip")
    public ResponseEntity<?> createTip(@RequestBody TipRequest tipRequest,
                                 UriComponentsBuilder builder){
        Tip tip = tipConverter.requestToTip(tipRequest);
        Tip tipCreated = tipService.createTip(tip);

        Long id = tipCreated.getId();
        UriComponents uriComponents = builder.path("/api/tip/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(tipConverter.tipToResponse(tipCreated), headers, HttpStatus.CREATED);

    }




}
