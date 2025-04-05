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

    // Liste des inscriptions aux formations
    @Override
    public List<FormationStagiaire> listerInscriptions() {
        return formationStagiaireRepository.findAll();
    }

    // Liste des stagiaires inscrits à une formation
    @Override
    public List<Stagiaire> listerStagiairesInscrits(Long formationId) {
        return formationStagiaireRepository.findStagiairesByFormationId(formationId);
    }
    // Liste des formations auxquelles un stagiaire est inscrit
    @Override
    public List<FormationStagiaire> listerFormationsStagiaire(Long stagiaireId) {
        return formationStagiaireRepository.findByStagiaireId(stagiaireId);
    }

    // Liste des inscriptions inscrits à une formation avec paiement effectué
    @Override
    public List<FormationStagiaire> listerPaiementsEffectues(Long formationId) {
        return formationStagiaireRepository.findStagiairesByFormationIdAndPaiementEffectue(formationId, true);
    }

    // Liste des inscriptions inscrits à une formation avec paiement non effectué
    @Override
    public List<FormationStagiaire> listerPaiementsNonEffectues(Long formationId) {
        return formationStagiaireRepository.findStagiairesByFormationIdAndPaiementEffectue(formationId, false);
    }

    // verifier si un stagiaire est inscrit à une formation
    @Override
    public boolean estInscrit(Long stagiaireId, Long formationId) {
        return formationStagiaireRepository.findByStagiaireIdAndFormationId(stagiaireId, formationId).isPresent();
    }

    // get les formations inscrites par un stagiaire
    @Override
    public List<FormationStagiaire> getFormationsInscritesByStagiaire(Long stagiaireId) {
        return formationStagiaireRepository.findByStagiaireIdAndPaiementEffectue(stagiaireId, true);
    }

    // get les formations inscrites par un stagiaire avec paiement non effectué
    @Override
    public List<FormationStagiaire> getFormationsInscritesByStagiaireAndPaiementNonEffectue(Long stagiaireId) {
        return formationStagiaireRepository.findByStagiaireIdAndPaiementEffectue(stagiaireId, false);
    }
    
}
