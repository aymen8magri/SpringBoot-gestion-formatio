package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Formation;

import java.util.List;

public interface IFormationService {
    // Ajout des formations dans la base de données
    Formation ajouterFormation(Formation formation);

    // Modification des formations dans la base de données
    Formation modifierFormation(Long id, Formation formation);

    // Suppression des formations dans la base de données
    void supprimerFormation(Long id);

    // Consultation des formations dans la base de données
    Formation consulterFormation(Long id);

    // Liste des formations dans la base de données
    List<Formation> listerFormations();
}
