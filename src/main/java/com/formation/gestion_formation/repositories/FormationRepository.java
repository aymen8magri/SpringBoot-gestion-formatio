package com.formation.gestion_formation.repositories;

import com.formation.gestion_formation.entities.Formation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {

    //liste des formations par formateur
    List<Formation> findByFormateurId(Long id);

    //liste des formations par entreprise
    List<Formation> findByEntrepriseId(Long id);
}
