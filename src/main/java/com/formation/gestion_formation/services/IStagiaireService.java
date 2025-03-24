package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Stagiaire;

import java.util.List;

public interface IStagiaireService {
    // Ajout des stagiaires dans la base de données
    Stagiaire ajouterStagiaire(Stagiaire stagiaire);

    // Modification des stagiaires dans la base de données
    Stagiaire modifierStagiaire(Long id, Stagiaire stagiaire);

    // Suppression des stagiaires dans la base de données
    void supprimerStagiaire(Long id);

    // Consultation des stagiaires dans la base de données
    Stagiaire consulterStagiaire(Long id);

    // Liste des stagiaires dans la base de données
    List<Stagiaire> listerStagiaires();
}
