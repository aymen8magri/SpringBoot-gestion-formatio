package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private String logoUrl = "uploads/entreprise.png";

    //relation entre entreprise et formation
    @JsonIgnore
    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private List<Formation> formations;

    //relation entre entreprise et adresse
    @OneToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    public Entreprise() {
    }

    public Entreprise(String nom, String email, String telephone, String logoUrl, Adresse adresse) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.logoUrl = logoUrl;
        this.adresse = adresse;
    }

    //getters and setters
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = (logoUrl == null || logoUrl.isEmpty()) ? "uploads/entreprise.png" : logoUrl;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    
}
