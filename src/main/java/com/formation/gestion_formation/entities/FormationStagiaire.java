package com.formation.gestion_formation.entities;

import jakarta.persistence.*;

import java.time.LocalDate;



@Entity
public class FormationStagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateInscription;
    private boolean paiementEffectue;

    //relation entre formationStragiaire et formation
    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    //relation entre formationStragiaire et stagiaire
    @ManyToOne
    @JoinColumn(name = "stagiaire_id")
    private Stagiaire stagiaire;

    public FormationStagiaire() {
    }

    public FormationStagiaire(LocalDate dateInscription, boolean paiementEffectue, Formation formation, Stagiaire stagiaire) {
        this.dateInscription = dateInscription;
        this.paiementEffectue = paiementEffectue;
        this.formation = formation;
        this.stagiaire = stagiaire;
    }

    //les getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public boolean isPaiementEffectue() {
        return paiementEffectue;
    }

    public void setPaiementEffectue(boolean paiementEffectue) {
        this.paiementEffectue = paiementEffectue;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }
}

