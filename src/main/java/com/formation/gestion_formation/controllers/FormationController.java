package com.formation.gestion_formation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formation.gestion_formation.entities.Formation;
import com.formation.gestion_formation.services.IFormationService;

@RestController
public class FormationController {
    @Autowired
    private IFormationService formationService;

    //Ajouter une nouvelle formation
    @PostMapping("/addFormation")
    public Formation ajouterFormation(@RequestBody Formation formation) {
        return formationService.ajouterFormation(formation);
    }

    //Modifier une formation existante
    @PutMapping("/modifierFormations/{id}")
    public Formation modifierFormation(@PathVariable Long id, @RequestBody Formation formation) {
        return formationService.modifierFormation(id, formation);
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
