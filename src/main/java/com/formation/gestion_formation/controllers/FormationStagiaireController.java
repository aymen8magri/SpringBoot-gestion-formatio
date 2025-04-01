package com.formation.gestion_formation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.gestion_formation.entities.FormationStagiaire;
import com.formation.gestion_formation.services.IFormationStagiaireService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FormationStagiaireController {
    @Autowired
    private IFormationStagiaireService formationStagiaireService;

    // Inscription d'un stagiaire à une formation
    @PostMapping("/inscrireStagiaire/{formationId}/{stagiaireId}")
    public FormationStagiaire inscrireStagiaire(@PathVariable Long formationId, @PathVariable Long stagiaireId) {
         return formationStagiaireService.inscrireStagiaire(formationId, stagiaireId);
    }

    // Modification de l'inscription d'un stagiaire à une formation
    @PutMapping("/modifierInscription/{id}/{paiementEffectue}")
    public FormationStagiaire modifierInscription(@PathVariable Long id, @PathVariable boolean paiementEffectue) {
        return formationStagiaireService.modifierInscription(id, paiementEffectue);
    }

    // Suppression de l'inscription d'un stagiaire à une formation
    @DeleteMapping("/supprimerInscription/{id}")
    public void supprimerInscription(@PathVariable Long id) {
        formationStagiaireService.supprimerInscription(id);
    }

    // Consulter une inscription par ID
    @GetMapping("/consulterInscription/{id}")
    public FormationStagiaire consulterInscription(@PathVariable Long id) {
        return formationStagiaireService.consulterInscription(id);
    }

    // Lister toutes les inscriptions
    @GetMapping("/listerInscriptions")
    public List<FormationStagiaire> listerInscriptions() {
        return formationStagiaireService.listerInscriptions();
    }
}
