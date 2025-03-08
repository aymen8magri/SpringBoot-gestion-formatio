package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    @Lob
    private String photo;

    //relation entre stagiaires et formation
    @ManyToMany(mappedBy = "stagiaires")
    private List<Formation> formations;
}
