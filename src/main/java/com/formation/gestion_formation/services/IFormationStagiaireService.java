package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.FormationStagiaire;
import com.formation.gestion_formation.entities.Stagiaire;

import java.util.List;

public interface IFormationStagiaireService {
    // Inscription d'un stagiaire à une formation
    FormationStagiaire inscrireStagiaire(Long formationId, Long stagiaireId);

    // Modification de l'inscription d'un stagiaire à une formation
    FormationStagiaire modifierInscription(Long id, boolean paiementEffectue);

    // Suppression de l'inscription d'un stagiaire à une formation
    void supprimerInscription(Long id);

    // Consultation de l'inscription d'un stagiaire à une    formation
    FormationStagiaire consulterInscription(Long id);

    // get all
    List<FormationStagiaire> listerInscriptions();

    // Liste des stagiaires inscrits à une formation
    List<Stagiaire> listerStagiairesInscrits(Long formationId);

    // Liste des formations auxquelles un stagiaire est inscrit
    List<FormationStagiaire> listerFormationsStagiaire(Long stagiaireId);

    // Liste des inscriptions à une formation avec paiement effectué
    List<FormationStagiaire> listerPaiementsEffectues(Long formationId);

    // Liste des inscriptions à une formation avec paiement non effectué
    List<FormationStagiaire> listerPaiementsNonEffectues(Long formationId);

    // verifier si un stagiaire est inscrit à une formation
    boolean estInscrit(Long stagiaireId, Long formationId);

    // get les formations inscrites par un stagiaire
    List<FormationStagiaire> getFormationsInscritesByStagiaire(Long stagiaireId);

    // get les formations inscrites par un stagiaire avec paiement non effectué
    List<FormationStagiaire> getFormationsInscritesByStagiaireAndPaiementNonEffectue(Long stagiaireId);

    
}
