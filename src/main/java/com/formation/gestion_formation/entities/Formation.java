package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nbrePlace;
    private int duree;
    private double prix;
    private String imageUrl = "uploads/formation.png";

    //relation entre formation et formateur
    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;


    //relation entre formation et entreprise
    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    //relation entre formation et formationStagiaire
    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private List<FormationStagiaire> inscriptions;


    public Formation() {
    }

    public Formation(String titre, String description, LocalDate dateDebut, LocalDate dateFin,int nbrePlace, int duree, double prix, String imageUrl, Formateur formateur, Entreprise entreprise) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrePlace = nbrePlace;
        this.duree = duree;
        this.prix = prix;
        this.imageUrl = imageUrl;
        this.formateur = formateur;
        this.entreprise = entreprise;
    }

    // les getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public int getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(int nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public List<FormationStagiaire> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<FormationStagiaire> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
