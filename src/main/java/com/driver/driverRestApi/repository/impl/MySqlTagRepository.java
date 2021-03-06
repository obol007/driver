package com.driver.driverRestApi.repository.impl;

import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.repository.TagRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("MySQL")
public interface MySqlTagRepository extends TagRepository, JpaRepository<Tag, Long> {

    @Query(value = "SELECT COUNT(`tags_id`) FROM `tip_tags` WHERE `tags_id` = ?",nativeQuery = true)
    Long isInUse(Long id);
}



