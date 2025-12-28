package com.yeditepe.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Student API",
				version = "1.0",
				description = "API documentation for managing students"
		)
)

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String... args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
