package com.formation.gestion_formation.services;

import java.util.List;

import com.formation.gestion_formation.entities.Formateur;

public interface IFormateurService {
    Formateur ajouterFormateur(Formateur formateur);
    Formateur modifierFormateur(Long id, Formateur formateur);
    void supprimerFormateur(Long id);
    Formateur consulterFormateur(Long id);
    List<Formateur> listerFormateurs();
}
