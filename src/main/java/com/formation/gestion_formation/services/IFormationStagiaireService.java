package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.FormationStagiaire;

import java.util.List;

public interface IFormationStagiaireService {
    // Inscription d'un stagiaire à une formation
    FormationStagiaire inscrireStagiaire(Long formationId, Long stagiaireId);

    // Modification de l'inscription d'un stagiaire à une formation
    FormationStagiaire modifierInscription(Long id, boolean paiementEffectue);

    // Suppression de l'inscription d'un stagiaire à une formation
    void supprimerInscription(Long id);

    // Consultation de l'inscription d'un stagiaire à une formation
    FormationStagiaire consulterInscription(Long id);

    // Liste des inscriptions des stagiaires aux formations
    List<FormationStagiaire> listerInscriptions();
}
