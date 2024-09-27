package com.driver.driverRestApi.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "content",callSuper = true)
public class Multimedia extends BaseEntity{

    private String name;
    @Column(nullable = false)
    private String contentType;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] content;


}
