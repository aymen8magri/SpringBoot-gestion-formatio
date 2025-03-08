package com.formation.gestion_formation.repositories;

import com.formation.gestion_formation.entities.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
}
