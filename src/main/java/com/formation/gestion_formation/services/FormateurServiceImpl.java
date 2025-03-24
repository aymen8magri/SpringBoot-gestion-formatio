package com.formation.gestion_formation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.gestion_formation.entities.Formateur;
import com.formation.gestion_formation.repositories.FormateurRepository;

@Service
public class FormateurServiceImpl implements IFormateurService {
    @Autowired
    private FormateurRepository formateurRepository;

    // Ajout d'un formateur dans la base de données
    @Override
    public Formateur ajouterFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    // Modification d'un formateur dans la base de données
    @Override
    public Formateur modifierFormateur(Long id, Formateur formateur) {
        return formateurRepository.findById(id).map(existingFormateur -> {
            existingFormateur.setNom(formateur.getNom());
            existingFormateur.setPrenom(formateur.getPrenom());
            existingFormateur.setEmail(formateur.getEmail());
            existingFormateur.setTelephone(formateur.getTelephone());
            existingFormateur.setPhoto(formateur.getPhoto());
            existingFormateur.setSpecialite(formateur.getSpecialite());
            existingFormateur.setExperience(formateur.getExperience());
            existingFormateur.setFormations(formateur.getFormations());
            return formateurRepository.save(existingFormateur);
        }).orElseThrow(() -> new RuntimeException("Formateur avec ID " + id + " non trouvé"));
    }

    // Suppression d'un formateur dans la base de données
    @Override
    public void supprimerFormateur(Long id) {
        formateurRepository.deleteById(id);
    }

    // Consultation d'un formateur dans la base de données
    @Override
    public Formateur consulterFormateur(Long id) {
        return formateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));
    }

    // Liste des formateurs dans la base de données
    @Override
    public List<Formateur> listerFormateurs() {
        return formateurRepository.findAll();
    }
}
