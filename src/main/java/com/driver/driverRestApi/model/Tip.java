package com.driver.driverRestApi.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class Tip extends BaseEntity {

    @Transient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    private String title;


    private String description;

    @ManyToMany
    private Set<Tag> tags;

    @OneToMany(mappedBy = "tip")
    private List<Question> questions;


    @PrePersist
    public void setCreated(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String text = localDateTime.format(formatter);
        this.created = LocalDateTime.parse(text,formatter);

    }
    @PreUpdate
    public void setUpdated(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String text = localDateTime.format(formatter);
        this.updated = LocalDateTime.parse(text,formatter);
    }


}


