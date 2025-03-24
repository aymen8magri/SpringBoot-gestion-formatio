package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;

    //relation entre adresse et entreprise
    @OneToOne(mappedBy = "adresse")
    private Entreprise entreprise;

    public Adresse() {
    }

    public Adresse(String rue, String ville, String codePostal, String pays) {
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }

}

