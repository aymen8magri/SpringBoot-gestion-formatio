package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Formation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFormationService {
    // Ajout des formations dans la base de données
    Formation ajouterFormation(Formation formation, MultipartFile file);

    // Modification des formations dans la base de données
    Formation modifierFormation(Long id, Formation formation, MultipartFile file);

    // Suppression des formations dans la base de données
    void supprimerFormation(Long id);

    // Consultation des formations dans la base de données
    Formation consulterFormation(Long id);

    // Liste des formations dans la base de données
    List<Formation> listerFormations();

    // Liste des formations par formateur
    List<Formation> listerFormationsParFormateur(Long id);

    // Liste des formations par entreprise
    List<Formation> listerFormationsParEntreprise(Long id);
}
