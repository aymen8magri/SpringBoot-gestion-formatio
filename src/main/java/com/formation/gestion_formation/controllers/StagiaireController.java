package com.formation.gestion_formation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formation.gestion_formation.entities.Stagiaire;
import com.formation.gestion_formation.services.IStagiaireService;

@RestController
public class StagiaireController {
    @Autowired
    private IStagiaireService stagiaireService;

    // Ajout d'un stagiaire
    @PostMapping("/addStagiaire")
    public Stagiaire ajouterStagiaire(@RequestBody Stagiaire stagiaire) {
        return stagiaireService.ajouterStagiaire(stagiaire);
    }

    // Modification d'un stagiaire
    @PutMapping("/{id}")
    public Stagiaire modifierStagiaire(@PathVariable Long id, @RequestBody Stagiaire stagiaire) {
        return stagiaireService.modifierStagiaire(id, stagiaire);
    }

    // Suppression d'un stagiaire
    @PostMapping("/deleteStagiaire/{id}")
    public void supprimerStagiaire(@PathVariable Long id) {
        stagiaireService.supprimerStagiaire(id);
    }

    // Consulter un stagiaire par ID
    @PostMapping("/consulterStagiaire/{id}")
    public Stagiaire consulterStagiaire(@PathVariable Long id) {
        return stagiaireService.consulterStagiaire(id);
    }

    // Lister tous les stagiaires
    @PostMapping("/listerStagiaires")
    public List<Stagiaire> listerStagiaires() {
        return stagiaireService.listerStagiaires();
    }
}
