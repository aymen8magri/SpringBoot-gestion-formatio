package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int duree;
    private double prix;
    @Lob
    private String image;

    //relation entre formation et formateur
    @OneToOne
    @JoinColumn(name = "formateur_id", unique = true)
    private Formateur formateur;

    //relation entre formation et entreprise
    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    //relation entre formation et stagiaiare
    @ManyToMany
    @JoinTable(
            name = "formation_stagiaire",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaire_id")
    )
    private List<Stagiaire> stagiaires;
}
