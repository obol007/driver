package com.driver.driverRestApi.controller;

import com.driver.driverRestApi.assembler.QuestionAssembler;
import com.driver.driverRestApi.converter.QuestionConverter;
import com.driver.driverRestApi.dto.request.QuestionRequest;
import com.driver.driverRestApi.dto.response.QuestionResponse;
import com.driver.driverRestApi.model.Question;
import com.driver.driverRestApi.service.impl.QuestionService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api")
public class QuestionController {

    private final QuestionService qService;
    private final QuestionConverter qConverter;
    private final QuestionAssembler qAssembler;

    public QuestionController(QuestionService qService,
                              QuestionConverter qConverter,
                              QuestionAssembler qAssembler) {
        this.qService = qService;
        this.qConverter = qConverter;
        this.qAssembler = qAssembler;
    }

    @GetMapping("/question")
    @ApiOperation(value = "Show all questions")
    public CollectionModel<?> getQuestions() {
        List<Question> questions = qService.getAll();
        List<EntityModel<QuestionResponse>> qEntities = questions.stream()
                .map(qConverter::qToResponse)
                .map(qAssembler::toModelWithAllQuestion)
                .collect(Collectors.toList());
        return new CollectionModel<>(qEntities,
                linkTo(methodOn(QuestionController.class).getQuestions()).withSelfRel());
    }


    @GetMapping("/tip/{tipId}/question")
    @ApiOperation(value = "Show all questions for a given tip ")
    public CollectionModel<?> getQuestions(@PathVariable("tipId") Long id) {
        List<Question> questions = qService.allByTipId(id);
        List<EntityModel<QuestionResponse>> qEntities = questions.stream()
                .map(qConverter::qToResponse)
                .map(qAssembler::toModel)
                .collect(Collectors.toList());
        //TODO: implement the method in the assembler
        return new CollectionModel<>(qEntities,
                linkTo(methodOn(QuestionController.class).getQuestions(id)).withSelfRel());
    }

    @GetMapping("/tip/{tipId}/question/{questionId}")
    @ApiOperation(value = "Show a single question for a given id")
    public ResponseEntity<?> getQuestion(@PathVariable("questionId") Long id, @PathVariable("tipId") Long tipId) {
        Question question = qService.oneById(id, tipId);
        EntityModel<QuestionResponse> qEntity = qAssembler.toModel(qConverter.qToResponse(question));
        return ResponseEntity.ok().body(qEntity);
    }

    @PostMapping("/tip/{tipId}/question")
    @ApiOperation(value = "Create a question for a given tip")
    public ResponseEntity<?> addQuestion(@PathVariable("tipId") Long tipId,
                                         @Valid @RequestBody QuestionRequest qRequest) {
        Question q = qService.add(qRequest, tipId);
        EntityModel<QuestionResponse> qEntity = qAssembler.toModel(qConverter.qToResponse(q));
        return ResponseEntity
                .created(qEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(qEntity);
    }

    @PutMapping("/tip/{tipId}/question/{questionId}")
    @ApiOperation(value = "Update a question for a given tip")
    public ResponseEntity<?> updateQuestion(@PathVariable("questionId") Long qId, @PathVariable("tipId") Long tipId,
                                            @Valid @RequestBody QuestionRequest qRequest) {
        Question q = qService.update(qRequest, tipId, qId);
        EntityModel<QuestionResponse> qEntity = qAssembler.toModel(qConverter.qToResponse(q));
        return ResponseEntity.ok().body(qEntity);
    }

    @DeleteMapping("/tip/{tipId}/question/{questionId}")
    @ApiOperation(value = "Delete a question for a given tip")
//    @ApiResponses(@ApiResponse(code= 200, message = "Question has been deleted!"))
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long qId, @PathVariable("tipId") Long tipId ){
        qService.delete(qId, tipId);
//        return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(String.format("Question with id: '%s' has been deleted!",qId));

    }

}


