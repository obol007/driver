package com.driver.driverRestApi.repository;

import com.driver.driverRestApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
