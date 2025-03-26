package com.formation.gestion_formation.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.formation.gestion_formation.entities.Entreprise;

public interface IEntrepriseService {
    Entreprise ajouterEntreprise(Entreprise entreprise, MultipartFile file);
    List<Entreprise> getAllEntreprises();
    Entreprise getEntrepriseById(Long id);
    Entreprise modifierEntreprise(Long id, Entreprise entreprise, MultipartFile file);
    void supprimerEntreprise(Long id);
}
