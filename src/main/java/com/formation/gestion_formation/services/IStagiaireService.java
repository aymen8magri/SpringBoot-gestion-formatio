package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Stagiaire;

import java.util.List;

public interface IStagiaireService {
    Stagiaire ajouterStagiaire(Stagiaire stagiaire);
    Stagiaire modifierStagiaire(Long id, Stagiaire stagiaire);
    void supprimerStagiaire(Long id);
    Stagiaire consulterStagiaire(Long id);
    List<Stagiaire> listerStagiaires();
}
