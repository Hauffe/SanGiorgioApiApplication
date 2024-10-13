package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SanGiorgioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanGiorgioApiApplication.class, args);
	}

}
