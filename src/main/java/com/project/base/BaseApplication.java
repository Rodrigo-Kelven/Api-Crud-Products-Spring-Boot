package com.project.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;


@SpringBootApplication
@RestController
@Tag(name = "API Status", description = "Endpoint of verefication of API.")
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
	
	@GetMapping("/")
    public String hello() {
      return String.format("Hello World ;)");
    }

	
}
