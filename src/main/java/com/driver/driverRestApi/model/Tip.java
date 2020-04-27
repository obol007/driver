package com.driver.driverRestApi.model;

import com.driver.driverRestApi.dto.response.TagResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.EntityModel;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Tip extends BaseEntity {

    @Transient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    @CreatedDate
    private LocalDateTime created;

    private String title;


    private String description;

    @ManyToMany
    private Set<Tag> tags;


    @PrePersist
    public void setLocalDateTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String text = localDateTime.format(formatter);
        this.created = LocalDateTime.parse(text,formatter);

    }

//
//    String timeColonPattern = "hh:mm:ss a";
//    DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
//    LocalTime colonTime = LocalTime.now();
//System.out.println(timeColonFormatter.format(colonTime));
//
//    The generated output is “05:35:50 PM“.
//    @PrePersist
//    public void PrePersist(){
//        setLocalDateTime(LocalDateTime.now());
//    }

}


