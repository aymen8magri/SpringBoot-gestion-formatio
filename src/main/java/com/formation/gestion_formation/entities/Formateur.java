package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private String specialite;
    private int experience;
    @Lob
    private String photo;

    //relation entre formateur et formation
    @OneToMany(mappedBy = "formateur")
    private List<Formation> formations;
}
