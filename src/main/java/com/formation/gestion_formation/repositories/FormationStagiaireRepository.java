package com.formation.gestion_formation.repositories;

import com.formation.gestion_formation.entities.FormationStagiaire;
import com.formation.gestion_formation.entities.Stagiaire;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FormationStagiaireRepository extends JpaRepository<FormationStagiaire, Long> {
    // Liste des stagiaires inscrits à une formation (peu importe paiement)
    @Query("SELECT fs.stagiaire FROM FormationStagiaire fs WHERE fs.formation.id = :formationId")
    List<Stagiaire> findStagiairesByFormationId(Long formationId);

    // Liste des inscriptions inscrits à une formation un paiement effectué
    @Query("SELECT fs FROM FormationStagiaire fs WHERE fs.formation.id = :formationId AND fs.paiementEffectue = :paiementEffectue")
    List<FormationStagiaire> findStagiairesByFormationIdAndPaiementEffectue(Long formationId, boolean paiementEffectue);

    
    

    // Liste des inscriptions pour un stagiaire (formation + état de paiement)
    List<FormationStagiaire> findByStagiaireId(Long stagiaireId);

    // verifier si le stagiaire est inscrit à une formation
    Optional<FormationStagiaire> findByStagiaireIdAndFormationId(Long stagiaireId, Long formationId);

    // get les formations inscrites par un stagiaire
    @Query("SELECT fs FROM FormationStagiaire fs WHERE fs.stagiaire.id = :stagiaireId AND fs.paiementEffectue = :paiementEffectue")
    List<FormationStagiaire> findByStagiaireIdAndPaiementEffectue(Long stagiaireId, boolean paiementEffectue);


}
