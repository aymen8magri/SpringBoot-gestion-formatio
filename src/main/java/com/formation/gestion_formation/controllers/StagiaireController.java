package com.formation.gestion_formation.controllers;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formation.gestion_formation.entities.Stagiaire;
import com.formation.gestion_formation.services.IStagiaireService;

@RestController
public class StagiaireController {
    @Autowired
    private IStagiaireService stagiaireService;

    // Ajout d'un stagiaire
    @PostMapping("/addStagiaire")
    public Stagiaire ajouterStagiaire(
            @RequestPart(value = "stagiaire", required = false) String stagiaireJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Logger logger = LoggerFactory.getLogger(StagiaireController.class);

        Stagiaire stagiaire = new Stagiaire(); // Créer un objet vide par défaut

        if (stagiaireJson != null && !stagiaireJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule()); // Gère LocalDate
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                stagiaire = objectMapper.readValue(stagiaireJson, Stagiaire.class);
                logger.info("Reçu JSON Stagiaire: " + stagiaireJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Stagiaire: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Les données du stagiaire sont requises !");
        }

        return stagiaireService.ajouterStagiaire(stagiaire, file);
    }


    // Modification d'un stagiaire
    @PutMapping("/{id}")
    public Stagiaire modifierStagiaire(
            @PathVariable Long id,
            @RequestPart(value = "stagiaire", required = false) String stagiaireJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
    
        Logger logger = LoggerFactory.getLogger(StagiaireController.class);
    
        Stagiaire stagiaire = null;
    
        // Vérifier si le JSON du stagiaire est présent
        if (stagiaireJson != null && !stagiaireJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule()); // Pour gérer LocalDate
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    
                stagiaire = objectMapper.readValue(stagiaireJson, Stagiaire.class);
                logger.info("Reçu JSON Stagiaire: " + stagiaireJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Stagiaire: " + e.getMessage());
            }
        }
    
        return stagiaireService.modifierStagiaire(id, stagiaire, file);
    }
    



    // Suppression d'un stagiaire
    @DeleteMapping("/deleteStagiaire/{id}")
    public void supprimerStagiaire(@PathVariable Long id) {
        stagiaireService.supprimerStagiaire(id);
    }

    // Consulter un stagiaire par ID
    @GetMapping("/consulterStagiaire/{id}")
    public Stagiaire consulterStagiaire(@PathVariable Long id) {
        return stagiaireService.consulterStagiaire(id);
    }

    // Lister tous les stagiaires
    @GetMapping("/listerStagiaires")
    public List<Stagiaire> listerStagiaires() {
        return stagiaireService.listerStagiaires();
    }
}
