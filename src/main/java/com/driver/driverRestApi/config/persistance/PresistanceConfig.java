package com.driver.driverRestApi.config.persistance;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@EnableJpaRepositories("com.driver.driverRestApi.repository")
@EntityScan("com.driver.driverRestApi.model")

//@Configuration
class PersistanceConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .addScript("schema-expressions.sql")
                .addScript("data-expressions.sql")
                .build();
    }
}

