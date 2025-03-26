package com.formation.gestion_formation.controllers;

import java.util.List;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.gestion_formation.entities.Entreprise;
import com.formation.gestion_formation.services.IEntrepriseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EntrepriseController {
    @Autowired
    private IEntrepriseService entrepriseService;

    // Ajout d'une entreprise
    @PostMapping("/addEntreprise")
    public Entreprise ajouterEntreprise(
            @RequestPart(value = "entreprise", required = false) String entrepriseJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Logger logger = LoggerFactory.getLogger(EntrepriseController.class);

        Entreprise entreprise = new Entreprise(); // Créer un objet vide par défaut

        if (entrepriseJson != null && !entrepriseJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();

                entreprise = objectMapper.readValue(entrepriseJson, Entreprise.class);
                logger.info("Reçu JSON Entreprise: " + entrepriseJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Entreprise: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Les données de l'entreprise sont requises !");
        }

        return entrepriseService.ajouterEntreprise(entreprise, file);
    }

    // Modification d'une entreprise
    @PutMapping("/updateEntreprise/{id}")
    public Entreprise modifierEntreprise(
        @PathVariable Long id,
            @RequestPart(value = "entreprise", required = false) String entrepriseJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Logger logger = LoggerFactory.getLogger(EntrepriseController.class);

        Entreprise entreprise = null;

        if (entrepriseJson != null && !entrepriseJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();

                entreprise = objectMapper.readValue(entrepriseJson, Entreprise.class);
                logger.info("Reçu JSON Entreprise: " + entrepriseJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Entreprise: " + e.getMessage());
            }
        }

        return entrepriseService.modifierEntreprise(id, entreprise, file);
    }

    // Récupérer toutes les entreprises
    @GetMapping("/getAllEntreprises")
    public List<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }

    // Récupérer une entreprise par son ID
    @GetMapping("/getEntreprise/{id}")
    public Entreprise getEntrepriseById(@PathVariable Long id) {
        return entrepriseService.getEntrepriseById(id);
    }

    // Supprimer une entreprise par son ID
    @DeleteMapping("/deleteEntreprise/{id}")
    public void deleteEntreprise(@PathVariable Long id) {
        entrepriseService.supprimerEntreprise(id);
    }

    
}
