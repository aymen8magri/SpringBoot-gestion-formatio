package com.formation.gestion_formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionFormationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionFormationApplication.class, args);
	}
	public void run(String... args) throws Exception {
		System.out.println("Gestion Formation");
	}
}
