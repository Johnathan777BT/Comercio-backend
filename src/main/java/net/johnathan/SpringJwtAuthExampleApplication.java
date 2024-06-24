package net.johnathan;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.johnathan.model.Comerciantes;
import net.johnathan.model.Departamentos;
import net.johnathan.model.Municipios;
import net.johnathan.repository.ComerciantesRepository;

@SpringBootApplication
public class SpringJwtAuthExampleApplication {

	@Autowired
	ComerciantesRepository repo;
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringJwtAuthExampleApplication.class, args);
		
	}

}
