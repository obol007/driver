package com.driver.driverRestApi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//@EnableSwagger2
//@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.driver.driverRestApi.controller"))
//                .paths(regex("/api.*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }



        @Bean
        public LinkDiscoverers discoverers() {
            List<LinkDiscoverer> plugins = new ArrayList<>();
            plugins.add(new CollectionJsonLinkDiscoverer());
            return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
        }



    private ApiInfo apiInfo() {
        return new ApiInfo(
                "DRIVER RESTful API",
                "RESTful Web Service",
                "1.0",
                "Terms of service",
                new Contact("Piotr Obolewicz", "https://www.github.com/obol007/driver", "p.obolewicz@gmail.com"),
                "LinkedIn",
                "http://www.linkedin.com/in/piotrobolewicz/", Collections.emptyList());
    }
}
