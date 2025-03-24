package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String specialite;
    private int experience;
    @Lob
    private byte[] photo;

    //relation entre formateur et formation
    @OneToMany(mappedBy = "formateur")
    private List<Formation> formations;

    public Formateur() {
    }

    public Formateur(String nom, String prenom, String email, String telephone, String specialite, int experience, byte[] photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.specialite = specialite;
        this.experience = experience;
        this.photo = photo;
    }

    //getter and setter
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getSpecialite() {
        return specialite;
    }
    public int getExperience() {
        return experience;
    }
    public byte[] getPhoto() {
        return photo;
    }
    public List<Formation> getFormations() {
        return formations;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

}
