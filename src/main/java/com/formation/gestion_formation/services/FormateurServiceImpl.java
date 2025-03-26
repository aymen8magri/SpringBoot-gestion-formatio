package com.formation.gestion_formation.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.formation.gestion_formation.entities.Formateur;
import com.formation.gestion_formation.repositories.FormateurRepository;

@Service
public class FormateurServiceImpl implements IFormateurService {
    @Autowired
    private FormateurRepository formateurRepository;

    // Ajout d'un formateur dans la base de données
    @Override
    public Formateur ajouterFormateur(Formateur formateur, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String folder = "uploads/";
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(folder + fileName);
            Files.write(path, file.getBytes());
                formateur.setPhotoUrl(folder + fileName);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de l'upload du fichier");
            }
        }
        return formateurRepository.save(formateur);
    }

    // Modification d'un formateur dans la base de données
    @Override
    public Formateur modifierFormateur(Long id, Formateur formateur, MultipartFile file) {
        return formateurRepository.findById(id)
                .map(formateurExistant -> {
                    if (formateur != null) {
                        if(formateur.getNom() != null) {
                            formateurExistant.setNom(formateur.getNom());
                        }
                        if(formateur.getPrenom() != null) {
                            formateurExistant.setPrenom(formateur.getPrenom());
                        }
                        if(formateur.getEmail() != null) {
                            formateurExistant.setEmail(formateur.getEmail());
                        }
                        if(formateur.getTelephone() != null) {
                            formateurExistant.setTelephone(formateur.getTelephone());
                        }
                        if(formateur.getSpecialite() != null) {
                            formateurExistant.setSpecialite(formateur.getSpecialite());
                        }
                        if(formateur.getExperience() != 0) {
                            formateurExistant.setExperience(formateur.getExperience());
                        }
                    }

                    if (file != null && !file.isEmpty()) {
                        try {
                            String folder = "uploads/";
                            String fileName = file.getOriginalFilename();
                            Path path = Paths.get(folder + fileName);
                            Files.write(path, file.getBytes());

                            formateurExistant.setPhotoUrl(folder + fileName);
                        } catch (Exception e) {
                            throw new RuntimeException("Erreur lors de l'upload du fichier");
                        }
                    }
                    return formateurRepository.save(formateurExistant);
                })
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));
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
