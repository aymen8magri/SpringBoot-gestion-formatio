package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Stagiaire;
import com.formation.gestion_formation.repositories.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.List;

@Service
public class StagiaireServiceImpl implements IStagiaireService {
    @Autowired
    private StagiaireRepository stagiaireRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    // Ajout des stagiaires dans la base de données
    @Override
    public Stagiaire ajouterStagiaire(Stagiaire stagiaire, MultipartFile file) {
    if (file != null && !file.isEmpty()) {
        try {
            String folder = "uploads/";
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(folder + fileName);
            Files.write(path, file.getBytes());

            stagiaire.setPhotoUrl(folder + fileName);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'upload du fichier");
        }
    }
     // Encoder le mot de passe avant d'enregistrer
     String encodedPassword = passwordEncoder.encode(stagiaire.getPassword());
     stagiaire.setPassword(encodedPassword);

    return stagiaireRepository.save(stagiaire);
}

    // Modification des stagiaires dans la base de données
    @Override
    public Stagiaire modifierStagiaire(Long id, Stagiaire stagiaire, MultipartFile file) {
        return stagiaireRepository.findById(id)
                .map(stagiaireExistant -> {
                    if (stagiaire != null) {
                        if(stagiaire.getNom() != null) {
                            stagiaireExistant.setNom(stagiaire.getNom());
                        }
                        if(stagiaire.getPrenom() != null) {
                            stagiaireExistant.setPrenom(stagiaire.getPrenom());
                        }
                        if(stagiaire.getEmail() != null) {
                            stagiaireExistant.setEmail(stagiaire.getEmail());
                        }
                        if(stagiaire.getTelephone() != null) {
                            stagiaireExistant.setTelephone(stagiaire.getTelephone());
                        }
                        if(stagiaire.getDateNaissance() != null) {
                            stagiaireExistant.setDateNaissance(stagiaire.getDateNaissance());
                        }
                    }

                    if (file != null && !file.isEmpty()) {
                        try {
                            String folder = "uploads/";
                            String fileName = file.getOriginalFilename();
                            Path path = Paths.get(folder + fileName);
                            Files.write(path, file.getBytes());
            
                            stagiaireExistant.setPhotoUrl(folder + fileName);
                        } catch (Exception e) {
                            throw new RuntimeException("Erreur lors de l'upload du fichier");
                        }
                    }

                    return stagiaireRepository.save(stagiaireExistant);
                })
                .orElseThrow(() -> new RuntimeException("Stagiaire avec ID " + id + " non trouvé"));
    }

    // Suppression des stagiaires dans la base de données
    @Override
    public void supprimerStagiaire(Long id) {
        stagiaireRepository.deleteById(id);
    }

    // Consultation des stagiaires dans la base de données
    @Override
    public Stagiaire consulterStagiaire(Long id) {
        return stagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stagiaire avec ID " + id + " non trouvé"));

    }

    // Liste des stagiaires dans la base de données
    @Override
    public List<Stagiaire> listerStagiaires() {
        return stagiaireRepository.findAll();
    }
}
