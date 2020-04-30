package com.driver.driverRestApi.repository.impl;

import com.driver.driverRestApi.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlTipRepository extends JpaRepository<Tip,Long> {

}
