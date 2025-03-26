package com.formation.gestion_formation.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.formation.gestion_formation.entities.Formateur;

public interface IFormateurService {
    Formateur ajouterFormateur(Formateur formateur, MultipartFile file);
    Formateur modifierFormateur(Long id, Formateur formateur, MultipartFile file);
    void supprimerFormateur(Long id);
    Formateur consulterFormateur(Long id);
    List<Formateur> listerFormateurs();
}
