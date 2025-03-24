package com.formation.gestion_formation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.formation.gestion_formation.entities.Adresse;
import com.formation.gestion_formation.entities.Entreprise;
import com.formation.gestion_formation.entities.Formateur;
import com.formation.gestion_formation.entities.Formation;
import com.formation.gestion_formation.entities.FormationStagiaire;
import com.formation.gestion_formation.entities.Stagiaire;
import com.formation.gestion_formation.repositories.AdresseRepository;
import com.formation.gestion_formation.repositories.EntrepriseRepository;
import com.formation.gestion_formation.repositories.FormateurRepository;
import com.formation.gestion_formation.repositories.FormationRepository;
import com.formation.gestion_formation.repositories.FormationStagiaireRepository;
import com.formation.gestion_formation.repositories.StagiaireRepository;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class GestionFormationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionFormationApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AdresseRepository adresseRepository,
                          EntrepriseRepository entrepriseRepository,
                          FormateurRepository formateurRepository,
                          FormationRepository formationRepository,
                          StagiaireRepository stagiaireRepository,
                          FormationStagiaireRepository inscriptionRepository) {
        return args -> {
            System.out.println("Insertion de données factices...");

            // **Création d'Adresses**
            Adresse addr1 = new Adresse("123 Rue Principale", "Tunis", "1000", "Tunisie");
            Adresse addr2 = new Adresse("456 Avenue Habib Bourguiba", "Sfax", "3000", "Tunisie");
            Adresse addr3 = new Adresse("789 Boulevard de la République", "Sousse", "4000", "Tunisie");
            adresseRepository.saveAll(Arrays.asList(addr1, addr2, addr3));

            // **Création d'Entreprises**
            Entreprise ent1 = new Entreprise("TechCorp", "contact@techcorp.com", "71234567", null, addr1);
            Entreprise ent2 = new Entreprise("EduServices", "info@eduservices.com", "71234568", null, addr2);
            Entreprise ent3 = new Entreprise("InnovDev", "support@innovdev.com", "71234569", null, addr3);
            entrepriseRepository.saveAll(Arrays.asList(ent1, ent2, ent3));

            // **Création des Formateurs**
            Formateur form1 = new Formateur("Ben Salah", "Ali", "ali.bensalah@gmail.com", "71234570", "Java Development", 5, null);
            Formateur form2 = new Formateur("Trabelsi", "Mouna", "mouna.trabelsi@gmail.com", "71234571", "Web Development", 8, null);
            Formateur form3 = new Formateur("Kacem", "Sami", "sami.kacem@gmail.com", "71234572", "Data Science", 4, null);
            formateurRepository.saveAll(Arrays.asList(form1, form2, form3));

            // **Création des Formations**
			Formation f1 =new Formation("Introduction à Java", "Bases du langage Java", LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5), 5, 300.00, null, form1, ent1);
			Formation f2 =new Formation("Développement Web", "Conception de sites web", LocalDate.of(2025, 4, 10), LocalDate.of(2025, 4, 15), 6, 400.00, null, form2, ent2);
			Formation f3 =new Formation("Data Science", "Analyse de données", LocalDate.of(2025, 4, 20), LocalDate.of(2025, 4, 25), 5, 350.00, null, form3, ent3);
            formationRepository.saveAll(Arrays.asList(f1, f2, f3));

            // **Création des Stagiaires**
            Stagiaire stag1 = new Stagiaire("Haddad", "Sarah", "sarah.haddad@gmail.com", "71234573", LocalDate.of(1995, 3, 15), null);
            Stagiaire stag2 = new Stagiaire("Mansour", "Omar", "omar.mansour@gmail.com", "71234574", LocalDate.of(1992, 7, 22), null);
            Stagiaire stag3 = new Stagiaire("Yahia", "Leila", "leila.yahia@gmail.com", "71234575", LocalDate.of(1998, 11, 5), null);
            stagiaireRepository.saveAll(Arrays.asList(stag1, stag2, stag3));

            // **Création des Inscriptions**
            FormationStagiaire insc1 = new FormationStagiaire(LocalDate.of(2025, 3, 10), true, f1, stag1);
            FormationStagiaire insc2 = new FormationStagiaire(LocalDate.of(2025, 3, 15), false, f2, stag2);
            FormationStagiaire insc3 = new FormationStagiaire(LocalDate.of(2025, 3, 20), true, f3, stag3);
            inscriptionRepository.saveAll(Arrays.asList(insc1, insc2, insc3));

            System.out.println("Insertion terminée !");
        };
    }
}
