package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Formation;
import com.formation.gestion_formation.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FormationServiceImpl implements IFormationService {
    @Autowired
    private FormationRepository formationRepository;

    // Ajout des formations dans la base de données
    @Override
    public Formation ajouterFormation(Formation formation,  MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String folder = "uploads/";
                String fileName = file.getOriginalFilename();
                formation.setImageUrl(folder + fileName);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de l'upload du fichier");
            }
        }
        return formationRepository.save(formation);
    }

    // Modification des formations dans la base de données
    @Override
    public Formation modifierFormation(Long id, Formation formation, MultipartFile file) {
        return formationRepository.findById(id)
                .map(formationExistant -> {
                    if (formation != null) {
                        if(formation.getTitre() != null) {
                            formationExistant.setTitre(formation.getTitre());
                        }
                        if(formation.getDescription() != null) {
                            formationExistant.setDescription(formation.getDescription());
                        }
                        if(formation.getDateDebut() != null) {
                            formationExistant.setDateDebut(formation.getDateDebut());
                        }
                        if(formation.getDateFin() != null) {
                            formationExistant.setDateFin(formation.getDateFin());
                        }
                        if(formation.getNbrePlace() != 0) {
                            formationExistant.setNbrePlace(formation.getNbrePlace());
                        }
                        if(formation.getDuree() != 0) {
                            formationExistant.setDuree(formation.getDuree());
                        }
                        if(formation.getPrix() != 0) {
                            formationExistant.setPrix(formation.getPrix());
                        }
                        if (formation.getPlanning() != null && !formation.getPlanning().isEmpty()) {
                            formationExistant.setPlanning(formation.getPlanning()); // ✅ Mise à jour du planning
                        }
                    }

                    if (file != null && !file.isEmpty()) {
                        try {
                            String folder = "uploads/";
                            String fileName = file.getOriginalFilename();
                            formationExistant.setImageUrl(folder + fileName);
                        } catch (Exception e) {
                            throw new RuntimeException("Erreur lors de l'upload du fichier");
                        }
                    }
                    return formationRepository.save(formationExistant);
                })
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
    }

    // Suppression des formations dans la base de données
    @Override
    public void supprimerFormation(Long id) {
        formationRepository.deleteById(id);
    }

    // Consultation des formations dans la base de données
    @Override
    public Formation consulterFormation(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
    }

    // Liste des formations dans la base de données
    @Override
    public List<Formation> listerFormations() {
        return formationRepository.findAll();
    }

    // Liste des formations par formateur
    @Override
    public List<Formation> listerFormationsParFormateur(Long id) {
        return formationRepository.findByFormateurId(id);
    }

    // Liste des formations par entreprise
    @Override
    public List<Formation> listerFormationsParEntreprise(Long id) {
        return formationRepository.findByEntrepriseId(id);
    }
}
