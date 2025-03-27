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
import java.util.List;

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
            Adresse addr4 = new Adresse("12 Rue de la Liberté", "Ariana", "2080", "Tunisie");
            Adresse addr5 = new Adresse("34 Avenue de France", "Bizerte", "7000", "Tunisie");
            adresseRepository.saveAll(Arrays.asList(addr1, addr2, addr3,addr4, addr5));

            // **Création d'Entreprises**
            Entreprise ent1 = new Entreprise("TechCorp", "contact@techcorp.com", "71234567", "uploads/entreprise.png", addr1);
            Entreprise ent2 = new Entreprise("EduServices", "info@eduservices.com", "71234568", "uploads/entreprise.png", addr2);
            Entreprise ent3 = new Entreprise("InnovDev", "support@innovdev.com", "71234569", "uploads/entreprise.png", addr3);
            Entreprise ent4 = new Entreprise("SoftTech", "contact@softtech.com", "71234580", "uploads/entreprise.png", addr4);
            Entreprise ent5 = new Entreprise("DataSolutions", "info@datasolutions.com", "71234581", "uploads/entreprise.png", addr5);
            entrepriseRepository.saveAll(Arrays.asList(ent1, ent2, ent3, ent4, ent5));

            // **Création des Formateurs**
            Formateur form1 = new Formateur("Ben Salah", "Ali", "ali.bensalah@gmail.com", "71234570", "Java Development", 5, "uploads/formateur.png");
            Formateur form2 = new Formateur("Trabelsi", "Mouna", "mouna.trabelsi@gmail.com", "71234571", "Web Development", 8, "uploads/formateur.png");
            Formateur form3 = new Formateur("Kacem", "Sami", "sami.kacem@gmail.com", "71234572", "Data Science", 4, "uploads/formateur.png");
            Formateur form4 = new Formateur("Bouzid", "Hassan", "hassan.bouzid@gmail.com", "71234582", "Cybersecurity", 6, "uploads/formateur.png");
            Formateur form5 = new Formateur("Mejri", "Nadia", "nadia.mejri@gmail.com", "71234583", "Cloud Computing", 7, "uploads/formateur.png");
            formateurRepository.saveAll(Arrays.asList(form1, form2, form3,form4, form5));

            // **Création des Formations**
			Formation f1 = new Formation(
                "Introduction à Java",
                "Bases du langage Java",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 6, 1),
                30,
                50,
                300.00,
                List.of(
                    "Jour 1: Introduction au Java",
                    "Jour 2: Variables et Types de données",
                    "Jour 3: Structures de contrôle (if, switch)",
                    "Jour 4: Boucles (for, while, do-while)",
                    "Jour 5: Programmation orientée objet (POO)",
                    "Jour 6: Manipulation des fichiers",
                    "Jour 7: Gestion des exceptions"
                ),
                "uploads/formation.png",
                form1,
                ent1
            );

            Formation f2 = new Formation(
                "Développement Web",
                "Conception de sites web modernes avec HTML, CSS et JavaScript",
                LocalDate.of(2025, 4, 10),
                LocalDate.of(2025, 5, 15),
                40,
                60,
                400.00,
                List.of(
                    "Jour 1: Introduction au développement web",
                    "Jour 2: HTML et structuration des pages",
                    "Jour 3: CSS et mise en page responsive",
                    "Jour 4: JavaScript et manipulation du DOM",
                    "Jour 5: Introduction à Bootstrap",
                    "Jour 6: Interactions avec les API",
                    "Jour 7: Déploiement d'un site web"
                ),
                "uploads/formation.png",
                form2,
                ent2
            );

            Formation f3 = new Formation(
                "Data Science",
                "Analyse et traitement des données avec Python",
                LocalDate.of(2025, 4, 20),
                LocalDate.of(2025, 6, 2),
                25,
                30,
                350.00,
                List.of(
                    "Jour 1: Introduction à la Data Science",
                    "Jour 2: Python pour la manipulation des données",
                    "Jour 3: Pandas et analyse exploratoire",
                    "Jour 4: Visualisation des données avec Matplotlib et Seaborn",
                    "Jour 5: Introduction aux modèles de Machine Learning",
                    "Jour 6: Régression et classification",
                    "Jour 7: Étude de cas et projet final"
                ),
                "uploads/formation.png",
                form3,
                ent3
            );
            Formation f4 = new Formation(
                "Sécurité Informatique",
                "Principes fondamentaux de la cybersécurité et des tests d'intrusion",
                LocalDate.of(2025, 5, 5),
                LocalDate.of(2025, 6, 30),
                20,
                40,
                450.00,
                List.of(
                    "Jour 1: Introduction à la cybersécurité",
                    "Jour 2: Cryptographie et chiffrement des données",
                    "Jour 3: Sécurité des réseaux",
                    "Jour 4: Détection et prévention des intrusions",
                    "Jour 5: Tests d'intrusion et audits de sécurité",
                    "Jour 6: Sécurité des applications web",
                    "Jour 7: Sécurité cloud et IoT"
                ),
                "uploads/formation.png",
                form4,
                ent4
            );

            Formation f5 = new Formation(
                "Cloud Computing",
                "Déploiement et gestion des services cloud",
                LocalDate.of(2025, 6, 10),
                LocalDate.of(2025, 7, 20),
                15,
                35,
                500.00,
                List.of(
                    "Jour 1: Introduction au Cloud Computing",
                    "Jour 2: Modèles de services cloud (IaaS, PaaS, SaaS)",
                    "Jour 3: Virtualisation et conteneurs (Docker, Kubernetes)",
                    "Jour 4: Stockage et gestion des données dans le cloud",
                    "Jour 5: Sécurité et conformité dans le cloud",
                    "Jour 6: Déploiement d'une application cloud",
                    "Jour 7: Étude de cas et mise en pratique"
                ),
                "uploads/formation.png",
                form5,
                ent5
            );

            formationRepository.saveAll(Arrays.asList(f1, f2, f3, f4,f5));
            
            // **Création des Stagiaires**
            Stagiaire stag1 = new Stagiaire("Haddad", "Sarah", "sarah.haddad@gmail.com", "71234573", LocalDate.of(1995, 3, 15), "uploads/stagiaire.png");
            Stagiaire stag2 = new Stagiaire("Mansour", "Omar", "omar.mansour@gmail.com", "71234574", LocalDate.of(1992, 7, 22), "uploads/stagiaire.png");
            Stagiaire stag3 = new Stagiaire("Yahia", "Leila", "leila.yahia@gmail.com", "71234575", LocalDate.of(1998, 11, 5), "uploads/stagiaire.png");
            Stagiaire stag4 = new Stagiaire("Gharbi", "Mohamed", "mohamed.gharbi@gmail.com", "71234584", LocalDate.of(1997, 4, 10), "uploads/stagiaire.png");
            Stagiaire stag5 = new Stagiaire("Saidi", "Fatma", "fatma.saidi@gmail.com", "71234585", LocalDate.of(1994, 8, 25), "uploads/stagiaire.png");
            stagiaireRepository.saveAll(Arrays.asList(stag1, stag2, stag3, stag4, stag5));

            // **Création des Inscriptions**
            FormationStagiaire insc1 = new FormationStagiaire(LocalDate.of(2025, 3, 10), true, f1, stag1);
            FormationStagiaire insc2 = new FormationStagiaire(LocalDate.of(2025, 3, 15), false, f2, stag2);
            FormationStagiaire insc3 = new FormationStagiaire(LocalDate.of(2025, 3, 20), true, f3, stag3);
            FormationStagiaire insc4 = new FormationStagiaire(LocalDate.of(2025, 4, 5), true, f4, stag4);
            FormationStagiaire insc5 = new FormationStagiaire(LocalDate.of(2025, 6, 15), false, f5, stag5);
            inscriptionRepository.saveAll(Arrays.asList(insc1, insc2, insc3, insc4, insc5));

            System.out.println("Insertion terminée !");
        };
    }

    // @Bean
    // CommandLineRunner clearDatabase(AdresseRepository adresseRepository,
    // EntrepriseRepository entrepriseRepository,
    // FormateurRepository formateurRepository,
    // FormationRepository formationRepository,
    // StagiaireRepository stagiaireRepository,
    // FormationStagiaireRepository inscriptionRepository) {
    // return args -> {
    // inscriptionRepository.deleteAll();
    // formationRepository.deleteAll();
    // formateurRepository.deleteAll();
    // entrepriseRepository.deleteAll();
    // adresseRepository.deleteAll();
    // stagiaireRepository.deleteAll();

    // System.out.println("Base de données vidée !");
    // };
    // }
}
