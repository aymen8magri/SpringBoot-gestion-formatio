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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formation.gestion_formation.entities.Formation;
import com.formation.gestion_formation.services.IFormationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FormationController {
    @Autowired
    private IFormationService formationService;

    //Ajouter une nouvelle formation
    @PostMapping("/addFormation")
    public Formation ajouterFormation(
            @RequestPart (value = "formation", required = false) String formationJson,
            @RequestPart (value = "file", required = false) MultipartFile file) {

        Logger logger = LoggerFactory.getLogger(FormationController.class);

        Formation formation = new Formation(); // Créer un objet vide par défaut

        if (formationJson != null && !formationJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule()); // Gère LocalDate
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                formation = objectMapper.readValue(formationJson, Formation.class);
                logger.info("Reçu JSON Formation: " + formationJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Formation: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Les données de la formation sont requises !");
        }

        return formationService.ajouterFormation(formation, file);
    }
    

    //Modifier une formation existante
    @PutMapping("/modifierFormations/{id}")
    public Formation modifierFormation(
            @PathVariable Long id,
            @RequestPart (value = "formation", required = false) String formationJson,
            @RequestPart (value = "file", required = false) MultipartFile file) {

        Logger logger = LoggerFactory.getLogger(FormationController.class);

        Formation formation = null;

        if (formationJson != null && !formationJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule()); // Gère LocalDate
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                formation = objectMapper.readValue(formationJson, Formation.class);
                logger.info("Reçu JSON Formation: " + formationJson);
            } catch (Exception e) {
                throw new RuntimeException("Erreur de conversion JSON en objet Formation: " + e.getMessage());
            }
        } 

        return formationService.modifierFormation(id, formation, file);
    }
   

    //Supprimer une formation
    @DeleteMapping("/deleteFormation/{id}")
    public void supprimerFormation(@PathVariable Long id) {
        formationService.supprimerFormation(id);
    }

    //Consulter une formation par ID
    @GetMapping("/consulterFormation/{id}")
    public Formation consulterFormation(@PathVariable Long id) {
        return formationService.consulterFormation(id);
    }

    //Lister toutes les formations
    @GetMapping("/formations")
    public List<Formation> listerFormations() {
        return formationService.listerFormations();
    }
}
