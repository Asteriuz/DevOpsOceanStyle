package br.com.fiap.oceanstyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OceanstyleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanstyleApplication.class, args);
	}

}