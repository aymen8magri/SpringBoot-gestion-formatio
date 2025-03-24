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

import com.formation.gestion_formation.entities.Formateur;
import com.formation.gestion_formation.services.IFormateurService;

@RestController
public class FormateurController {
    @Autowired
    private IFormateurService formateurService;

    // Ajout d'un formateur
    @PostMapping("/addFormateur")
    public Formateur ajouterFormateur(@RequestBody Formateur formateur) {
        return formateurService.ajouterFormateur(formateur);
    }

    // Modification d'un formateur
    @PutMapping("/modifierFormateur/{id}")
    public Formateur modifierFormateur(@PathVariable Long id, @RequestBody Formateur formateur) {
        return formateurService.modifierFormateur(id, formateur);
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
