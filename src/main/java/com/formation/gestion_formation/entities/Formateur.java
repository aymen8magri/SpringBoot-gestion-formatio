package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

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
}
