package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.TipAssembler;
import com.driver.driverRestApi.converter.TipConverter;
import com.driver.driverRestApi.dto.request.TipRequest;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.model.Tip;
import com.driver.driverRestApi.service.impl.TipService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/tip")
@Slf4j
public class TipController {

    TipService tipService;
    TipConverter tipConverter;
    TipAssembler assembler;

    public TipController(TipService tipService,
                         TipConverter tipConverter,
                         TipAssembler assembler) {
        this.tipService = tipService;
        this.tipConverter = tipConverter;
        this.assembler = assembler;
    }

    @GetMapping
    @ApiOperation(value = "Find all tips")
//    public List<TipResponse> getTips(){
//        return tipService.getTips().stream()
//                .map(tipConverter::tipToResponse)
//                .collect(Collectors.toList());
//    }

    public CollectionModel<EntityModel<TipResponse>> getTips() {
        List<Tip> tips = tipService.getTips();
        List<EntityModel<TipResponse>> tipEntities = tips.stream()
                .map(tipConverter::tipToResponse)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(tipEntities,
                linkTo(methodOn(TipController.class).getTips()).withSelfRel());
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
        log.warn("Tip request: "+tipRequest);
        Tip tip = tipConverter.requestToTip(tipRequest);
        log.warn("Tip converted: "+tip);
        Tip tipCreated = tipService.createTip(tip);
        log.warn("Tip created: "+tipCreated);

        Long id = tipCreated.getId();
        UriComponents uriComponents = builder.path("/api/tip/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(tipConverter.tipToResponse(tipCreated), headers, HttpStatus.CREATED);

    }




}
