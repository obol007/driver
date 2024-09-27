package com.driver.driverRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DriverRestApiApplication implements WebMvcConfigurer {

	@Override
	public void addViewControllers (ViewControllerRegistry registry) {
		RedirectViewControllerRegistration r =
				registry.addRedirectViewController("/", "/swagger-ui.html");
	}

	public static void main(String[] args) {
		SpringApplication.run(DriverRestApiApplication.class, args);
	}


}
