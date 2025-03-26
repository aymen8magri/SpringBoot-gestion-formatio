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
import com.formation.gestion_formation.entities.Formateur;
import com.formation.gestion_formation.services.IFormateurService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FormateurController {
    @Autowired
    private IFormateurService formateurService;

    // Ajout d'un formateur
    @PostMapping("/addFormateur")
    public Formateur ajouterFormateur(
            @RequestPart(value = "formateur", required = false) String formateurJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        Logger logger = LoggerFactory.getLogger(FormateurController.class);

        Formateur formateur = new Formateur(); // Créer un objet vide par défaut

        if (formateurJson != null && !formateurJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                formateur = objectMapper.readValue(formateurJson, Formateur.class);
                logger.info("Reçu JSON Formateur: " + formateurJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Formateur: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Les données du formateur sont requises !");
        }

        return formateurService.ajouterFormateur(formateur, file);
    }

    // Modification d'un formateur
    @PutMapping("/modifierFormateur/{id}")
    public Formateur modifierFormateur(
            @PathVariable Long id,
            @RequestPart(value = "formateur", required = false) String formateurJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        Logger logger = LoggerFactory.getLogger(FormateurController.class);

        Formateur formateur = null;

        if (formateurJson != null && !formateurJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                formateur = objectMapper.readValue(formateurJson, Formateur.class);
                logger.info("Reçu JSON Formateur: " + formateurJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Formateur: " + e.getMessage());
            }
        }

        return formateurService.modifierFormateur(id, formateur, file);
    }

    // Suppression d'un formateur
    @DeleteMapping("/deleteFormateur/{id}")
    public void supprimerFormateur(@PathVariable Long id) {
        formateurService.supprimerFormateur(id);
    }

    // Consulter un formateur par ID
    @GetMapping("/consulterFormateur/{id}")
    public Formateur consulterFormateur(@PathVariable Long id) {
        return formateurService.consulterFormateur(id);
    }

    // Lister tous les formateurs
    @GetMapping("/formateurs")
    public List<Formateur> listerFormateurs() {
        return formateurService.listerFormateurs();
    }
}
