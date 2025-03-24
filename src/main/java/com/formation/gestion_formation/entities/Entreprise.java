package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String email;
    private String telephone;
    @Lob
    private byte[] logo;

    //relation entre entreprise et formation
    @OneToMany(mappedBy = "entreprise")
    private List<Formation> formations;

    //relation entre entreprise et adresse
    @OneToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    public Entreprise() {
    }

    public Entreprise(String nom, String email, String telephone, byte[] logo, Adresse adresse) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.logo = logo;
        this.adresse = adresse;
    }

    //getters and setters

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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
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
