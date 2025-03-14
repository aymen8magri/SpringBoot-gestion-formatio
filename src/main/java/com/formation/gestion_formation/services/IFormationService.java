package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Formation;

import java.util.List;

public interface IFormationService {
    Formation ajouterFormation(Formation formation);
    Formation modifierFormation(Long id, Formation formation);
    void supprimerFormation(Long id);
    Formation consulterFormation(Long id);
    List<Formation> listerFormations();
}
