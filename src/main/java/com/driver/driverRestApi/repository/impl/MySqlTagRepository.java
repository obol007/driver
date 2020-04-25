package com.driver.driverRestApi.repository.impl;

import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.repository.TagRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("MySQL")
public interface MySqlTagRepository extends TagRepository, JpaRepository<Tag, Long> {


}



