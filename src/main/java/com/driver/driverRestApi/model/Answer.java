package com.driver.driverRestApi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Answer extends BaseEntity {


    private String answerText;
    private Boolean isCorrect;

    @ManyToOne
    private Question question;
}
