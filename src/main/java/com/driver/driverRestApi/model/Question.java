package com.driver.driverRestApi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseEntity{

    private String questionText;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @ManyToOne
    private Tip tip;
}
