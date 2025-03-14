package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Stagiaire;
import com.formation.gestion_formation.repositories.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StagiaireServiceImpl implements IStagiaireService {
    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Override
    public Stagiaire ajouterStagiaire(Stagiaire stagiaire) {
        return stagiaireRepository.save(stagiaire);
    }

    @Override
    public Stagiaire modifierStagiaire(Long id, Stagiaire stagiaire) {
        return stagiaireRepository.findById(id)
                .map(stagiaireExistant -> {
                    stagiaireExistant.setNom(stagiaire.getNom());
                    stagiaireExistant.setPrenom(stagiaire.getPrenom());
                    stagiaireExistant.setEmail(stagiaire.getEmail());
                    stagiaireExistant.setTelephone(stagiaire.getTelephone());
                    stagiaireExistant.setDateNaissance(stagiaire.getDateNaissance());
                    stagiaireExistant.setPhoto(stagiaire.getPhoto());
                    return stagiaireRepository.save(stagiaireExistant);
                })
                .orElseThrow(() -> new RuntimeException("Stagiaire avec ID " + id + " non trouvé"));
    }


    @Override
    public void supprimerStagiaire(Long id) {
        stagiaireRepository.deleteById(id);
    }

    @Override
    public Stagiaire consulterStagiaire(Long id) {
        return stagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stagiaire avec ID " + id + " non trouvé"));

    }

    @Override
    public List<Stagiaire> listerStagiaires() {
        return stagiaireRepository.findAll();
    }
}
