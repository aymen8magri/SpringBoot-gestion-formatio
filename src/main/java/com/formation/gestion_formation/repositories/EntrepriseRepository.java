package com.formation.gestion_formation.repositories;

import com.formation.gestion_formation.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
}
