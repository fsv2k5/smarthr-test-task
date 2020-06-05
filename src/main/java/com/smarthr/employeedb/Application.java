package com.smarthr.employeedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.smarthr")
@EnableJpaRepositories("com.smarthr.employeedb.*")
@EntityScan("com.smarthr.employeedb.*")
@SpringBootApplication
//		(exclude = {GsonAutoConfiguration.class,
//		DataSourceAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
