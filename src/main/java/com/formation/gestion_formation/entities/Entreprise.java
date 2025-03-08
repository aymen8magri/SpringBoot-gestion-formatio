package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    @Lob
    private String logo;

    //relation entre entreprise et formation
    @OneToMany(mappedBy = "entreprise")
    private List<Formation> formations;
}
