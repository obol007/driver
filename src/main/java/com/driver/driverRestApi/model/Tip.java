package com.driver.driverRestApi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Tip extends BaseEntity {

    @CreatedDate
    private LocalDateTime created;

    private String title;

    private String description;

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();


}


