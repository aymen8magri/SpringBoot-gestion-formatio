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
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    @Lob
    private byte[] photo;

    //relation entre stagiaires et formationStagiaire
    @OneToMany(mappedBy = "stagiaire")
    private List<FormationStagiaire> inscriptions;

    public Stagiaire() {
    }

    public Stagiaire(String nom, String prenom, String email, String telephone, LocalDate dateNaissance, byte[] photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.photo = photo;
    }
    
    //les getters and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<FormationStagiaire> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<FormationStagiaire> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
