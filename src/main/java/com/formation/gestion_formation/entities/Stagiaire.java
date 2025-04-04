package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String role = "STAGIAIRE"; // Role par défaut

    private String telephone;
    private LocalDate dateNaissance;
    private String photoUrl = "uploads/stagiaire.png";

    // relation entre stagiaires et formationStagiaire
    @JsonIgnore
    @OneToMany(mappedBy = "stagiaire", cascade = CascadeType.REMOVE)
    private List<FormationStagiaire> inscriptions;

    public Stagiaire() {
    }

    public Stagiaire(String nom, String prenom, String email, String password, String telephone,
            LocalDate dateNaissance, String photoUrl) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.photoUrl = photoUrl;
    }

    // les getters and setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = (photoUrl != null && !photoUrl.isEmpty()) ? photoUrl : "uploads/stagiaire.png";
    }

    public List<FormationStagiaire> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<FormationStagiaire> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
