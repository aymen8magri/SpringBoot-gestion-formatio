package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.FormationStagiaire;

import java.util.List;

public interface IFormationStagiaireService {
    FormationStagiaire inscrireStagiaire(Long formationId, Long stagiaireId);
    FormationStagiaire modifierInscription(Long id, boolean paiementEffectue);
    void supprimerInscription(Long id);
    FormationStagiaire consulterInscription(Long id);
    List<FormationStagiaire> listerInscriptions();
}
