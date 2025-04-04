package com.formation.gestion_formation.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.formation.gestion_formation.entities.Adresse;
import com.formation.gestion_formation.entities.Entreprise;
import com.formation.gestion_formation.repositories.AdresseRepository;
import com.formation.gestion_formation.repositories.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Entreprise ajouterEntreprise(Entreprise entreprise, MultipartFile file) {
        if (entreprise.getAdresse() != null) {
            // Sauvegarde d'abord l'adresse
            Adresse adresse = adresseRepository.save(entreprise.getAdresse());
            entreprise.setAdresse(adresse);
        }
        if (file != null && !file.isEmpty()) {
            try {
                String folder = "uploads/";
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(folder + fileName);
                Files.write(path, file.getBytes());

                entreprise.setLogoUrl(folder + fileName);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de l'upload du fichier");
            }
        }
        // Encoder le mot de passe avant d'enregistrer
    String encodedPassword = passwordEncoder.encode(entreprise.getPassword());
    entreprise.setPassword(encodedPassword);
        return entrepriseRepository.save(entreprise);
    }

    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    public Entreprise getEntrepriseById(Long id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise avec ID " + id + " non trouvée"));
    }

    public Entreprise modifierEntreprise(Long id, Entreprise entreprise, MultipartFile file) {
        return entrepriseRepository.findById(id)
                .map(entrepriseExistant -> {
                    if (entreprise != null) {
                        if(entreprise.getNom() != null) {
                            entrepriseExistant.setNom(entreprise.getNom());
                        }
                        
                        if(entreprise.getEmail() != null) {
                            entrepriseExistant.setEmail(entreprise.getEmail());
                        }
                        if(entreprise.getPassword() != null){
                            String encodedPassword = passwordEncoder.encode(entreprise.getPassword());
                            entrepriseExistant.setPassword(encodedPassword);
                        }
                        if(entreprise.getTelephone() != null) {
                            entrepriseExistant.setTelephone(entreprise.getTelephone());
                        }
                        if (entreprise.getAdresse() != null) {
                            Adresse adresseExistant = entrepriseExistant.getAdresse();
                            Adresse nouvelleAdresse = entreprise.getAdresse();
                            if (adresseExistant != null) { 
                                // On met à jour uniquement les champs modifiés
                                if (nouvelleAdresse.getRue() != null) {
                                    adresseExistant.setRue(nouvelleAdresse.getRue());
                                }
                                if (nouvelleAdresse.getVille() != null) {
                                    adresseExistant.setVille(nouvelleAdresse.getVille());
                                }
                                if (nouvelleAdresse.getCodePostal() != null) {
                                    adresseExistant.setCodePostal(nouvelleAdresse.getCodePostal());
                                }
                                if (nouvelleAdresse.getPays() != null) {
                                    adresseExistant.setPays(nouvelleAdresse.getPays());
                                }
                                entrepriseExistant.setAdresse(adresseExistant);
                            } else {
                                entrepriseExistant.setAdresse(nouvelleAdresse);
                            }
                        }
                    }

                    if (file != null && !file.isEmpty()) {
                        try {
                            String folder = "uploads/";
                            String fileName = file.getOriginalFilename();
                            Path path = Paths.get(folder + fileName);
                            Files.write(path, file.getBytes());

                            entrepriseExistant.setLogoUrl(folder + fileName);
                        } catch (Exception e) {
                            throw new RuntimeException("Erreur lors de l'upload du fichier");
                        }
                    }

                    return entrepriseRepository.save(entrepriseExistant);
                })
                .orElseThrow(() -> new RuntimeException("Entreprise avec ID " + id + " non trouvée"));
    }

    public void supprimerEntreprise(Long id) {
        Entreprise entreprise = entrepriseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

        // Vérifier si l'entreprise a encore des formations associées
        if (!entreprise.getFormations().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer : L'entreprise a encore des formations associées.");
        }

        // if(entreprise.getAdresse() != null) {
        //     adresseRepository.deleteById(entreprise.getAdresse().getId());
        // }

        entrepriseRepository.deleteById(id);
    }
    
}
