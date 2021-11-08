package com.dammak.project401;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com/dammak/project401/controller"})
public class Project401Application {

	public static void main(String[] args) {
		SpringApplication.run(Project401Application.class, args);
	}

}
