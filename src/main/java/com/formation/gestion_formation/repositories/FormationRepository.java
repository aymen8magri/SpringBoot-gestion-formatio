package com.formation.gestion_formation.repositories;

import com.formation.gestion_formation.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}
