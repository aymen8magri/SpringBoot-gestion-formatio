package com.formation.gestion_formation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.gestion_formation.entities.FormationStagiaire;
import com.formation.gestion_formation.services.IFormationStagiaireService;

@RestController
public class FormationStagiaireController {
    @Autowired
    private IFormationStagiaireService formationStagiaireService;

    // Inscription d'un stagiaire à une formation
    @PostMapping("/inscrireStagiaire/{formationId}/{stagiaireId}")
    public FormationStagiaire inscrireStagiaire(@PathVariable Long formationId, @PathVariable Long stagiaireId) {
         return formationStagiaireService.inscrireStagiaire(formationId, stagiaireId);
    }

    // Modification de l'inscription d'un stagiaire à une formation
    @PostMapping("/modifierInscription/{id}/{paiementEffectue}")
    public FormationStagiaire modifierInscription(@PathVariable Long id, @PathVariable boolean paiementEffectue) {
        return formationStagiaireService.modifierInscription(id, paiementEffectue);
    }

    // Suppression de l'inscription d'un stagiaire à une formation
    @PostMapping("/supprimerInscription/{id}")
    public void supprimerInscription(@PathVariable Long id) {
        formationStagiaireService.supprimerInscription(id);
    }

    // Consulter une inscription par ID
    @PostMapping("/consulterInscription/{id}")
    public FormationStagiaire consulterInscription(@PathVariable Long id) {
        return formationStagiaireService.consulterInscription(id);
    }

    // Lister toutes les inscriptions
    @PostMapping("/listerInscriptions")
    public List<FormationStagiaire> listerInscriptions() {
        return formationStagiaireService.listerInscriptions();
    }
}
