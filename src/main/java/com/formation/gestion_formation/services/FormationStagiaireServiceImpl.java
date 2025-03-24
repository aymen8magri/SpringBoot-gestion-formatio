package com.formation.gestion_formation.services;

import com.formation.gestion_formation.entities.Formation;
import com.formation.gestion_formation.entities.FormationStagiaire;
import com.formation.gestion_formation.entities.Stagiaire;
import com.formation.gestion_formation.repositories.FormationRepository;
import com.formation.gestion_formation.repositories.FormationStagiaireRepository;
import com.formation.gestion_formation.repositories.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Lazy
public class FormationStagiaireServiceImpl implements IFormationStagiaireService {
    @Autowired
    private FormationStagiaireRepository formationStagiaireRepository;
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private StagiaireRepository stagiaireRepository;

    // Inscription d'un stagiaire à une formation
    @Override
    public FormationStagiaire inscrireStagiaire(Long formationId, Long stagiaireId) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée avec ID : " + formationId));

        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
                .orElseThrow(() -> new RuntimeException("Stagiaire non trouvé avec ID : " + stagiaireId));

        FormationStagiaire inscription = new FormationStagiaire();
        inscription.setFormation(formation);
        inscription.setStagiaire(stagiaire);
        inscription.setDateInscription(LocalDate.now());
        inscription.setPaiementEffectue(false); // Par défaut, paiement non effectué

        return formationStagiaireRepository.save(inscription);
    }

    // Modification de l'inscription d'un stagiaire à une formation
    @Override
    public FormationStagiaire modifierInscription(Long id, boolean paiementEffectue) {
        return formationStagiaireRepository.findById(id)
                .map(inscription -> {
                    inscription.setPaiementEffectue(paiementEffectue);
                    return formationStagiaireRepository.save(inscription);
                })
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée avec ID : " + id));
    }

    // Suppression de l'inscription d'un stagiaire à une formation
    @Override
    public void supprimerInscription(Long id) {
        formationStagiaireRepository.deleteById(id);
    }

    // Consultation de l'inscription d'un stagiaire à une formation
    @Override
    public FormationStagiaire consulterInscription(Long id) {
        return formationStagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée avec ID : " + id));
    }

    // Liste des inscriptions des stagiaires aux formations
    @Override
    public List<FormationStagiaire> listerInscriptions() {
        return formationStagiaireRepository.findAll();
    }
}
