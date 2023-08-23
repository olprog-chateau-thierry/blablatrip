package com.formation.blablatrip;

import com.formation.blablatrip.entities.DestinationEntity;
import com.formation.blablatrip.services.DestinationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class BlablatripApplication {

	@Autowired
	private DestinationService destinationService;

	public static void main(String[] args) {
		SpringApplication.run(BlablatripApplication.class, args);
		log.info("Bonjour {}", "sam");
	}

	@Bean
	public void testDestinationService() {
		DestinationEntity destination = new DestinationEntity();
		destination.setNom("Lune de miel");
		destination.setPays("France");
		destination.setVille("Bora bora");
		destination.setPrixBase(389.99);
		destination.setDescription("Vive les vacances!!! ");

		try {
			destinationService.save(destination);
			log.info("Liste des destinations : {}", destinationService.findAll());
		} catch (Exception e) {
			log.error("DestinationService save : {}", e.getMessage());
		}
	}
}
