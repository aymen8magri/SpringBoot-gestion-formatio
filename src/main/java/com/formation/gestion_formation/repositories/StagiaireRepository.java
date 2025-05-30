package com.formation.gestion_formation.repositories;

import com.formation.gestion_formation.entities.Stagiaire;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    Optional<Stagiaire> findByEmail(String email);
}
