package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.TipAssembler;
import com.driver.driverRestApi.converter.TipConverter;
import com.driver.driverRestApi.dto.request.TipRequest;
import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.model.Tip;
import com.driver.driverRestApi.service.impl.TipService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> getTip(@PathVariable("id") Long id) {
        Tip tip = tipService.findTip(id);
        TipResponse tipResponse = tipConverter.tipToResponse(tip);
        //TODO: test the difference
//        return assembler.toModel(tipResponse);
        EntityModel<TipResponse> tipModel = assembler.toModel(tipResponse);
        return ResponseEntity.ok().body(tipModel);
    }

    @PostMapping
    @ApiOperation(value = "Create a tip")
    public ResponseEntity<?> createTip(@Valid @RequestBody TipRequest tipRequest) {
        Tip tipCreated = tipService.createTip(tipRequest);

        EntityModel<TipResponse> tipModel = assembler.toModel(tipConverter.tipToResponse(tipCreated));
        return ResponseEntity
                .created(tipModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(tipModel);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a tip")
    public ResponseEntity<?> updateTip(@RequestBody TipRequest tipRequest,
                                       @PathVariable Long id) {
        Boolean tipExists = tipService.doesExist(id);
        Tip tipUpdated = tipService.updateTip(tipRequest, id);
        EntityModel<TipResponse> tipModel = assembler.toModel(tipConverter.tipToResponse(tipUpdated));
        if(tipExists){
            return ResponseEntity.ok().body(tipModel);
        }
        return ResponseEntity
                .created(tipModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(tipModel);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a tip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tip deleted!")
    }
    )
    public ResponseEntity<?> deleteTip(@PathVariable Long id) {
        tipService.deleteTip(id);
//        return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(String.format("Tip with id: '%s' has been deleted!",id));
    }



}
