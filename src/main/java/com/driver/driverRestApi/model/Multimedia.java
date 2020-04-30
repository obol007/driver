package com.driver.driverRestApi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Multimedia extends BaseEntity{

    private String title;
    private String content;

    @OneToOne
    private Tip tip;
}
