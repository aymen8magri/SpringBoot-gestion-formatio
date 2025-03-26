package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Stagiaire;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IStagiaireService {
    // Ajout des stagiaires dans la base de données
    Stagiaire ajouterStagiaire(Stagiaire stagiaire, MultipartFile file);

    // Modification des stagiaires dans la base de données
    Stagiaire modifierStagiaire(Long id, Stagiaire stagiaire, MultipartFile file);

    // Suppression des stagiaires dans la base de données
    void supprimerStagiaire(Long id);

    // Consultation des stagiaires dans la base de données
    Stagiaire consulterStagiaire(Long id);

    // Liste des stagiaires dans la base de données
    List<Stagiaire> listerStagiaires();
}
