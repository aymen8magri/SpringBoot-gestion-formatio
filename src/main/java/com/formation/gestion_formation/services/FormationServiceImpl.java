package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Formation;
import com.formation.gestion_formation.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationServiceImpl implements IFormationService {
    @Autowired
    private FormationRepository formationRepository;

    @Override
    public Formation ajouterFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation modifierFormation(Long id, Formation formation) {
        return formationRepository.findById(id).map(formation1 -> {
            formation1.setTitre(formation.getTitre());
            formation1.setDescription(formation.getDescription());
            formation1.setDateDebut(formation.getDateDebut());
            formation1.setDateFin(formation.getDateFin());
            formation1.setDuree(formation.getDuree());
            formation1.setPrix(formation.getPrix());
            formation1.setImage(formation.getImage());
            formation1.setFormateur(formation.getFormateur());
            formation1.setEntreprise(formation.getEntreprise());
            formation1.setInscriptions(formation.getInscriptions());
            return formationRepository.save(formation1);
        }).orElseThrow(() -> new RuntimeException("Formation avec ID " + id + " non trouvée"));

    }

    @Override
    public void supprimerFormation(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public Formation consulterFormation(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
    }

    @Override
    public List<Formation> listerFormations() {
        return formationRepository.findAll();
    }
}
