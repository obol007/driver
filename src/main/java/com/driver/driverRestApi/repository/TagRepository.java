package com.driver.driverRestApi.repository;


import com.driver.driverRestApi.model.Tag;

import java.util.Optional;

public interface TagRepository {

   Optional<Tag> findById(Long id);

   Tag save(Tag tag);

}
