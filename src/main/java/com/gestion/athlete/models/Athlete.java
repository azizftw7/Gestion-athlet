package com.gestion.athlete.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Athlete {

    @Id
    private Long codeA;

    private String nomPrenom;

    private String genre;

    private Double taille;

    private Double poids;

    @OneToMany(mappedBy = "athlete", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resultat> resultats = new HashSet<>();

    // Constructors
    public Athlete() {}

    public Athlete(Long codeA, String nomPrenom, String genre, Double taille, Double poids) {
        this.codeA = codeA;
        this.nomPrenom = nomPrenom;
        this.genre = genre;
        this.taille = taille;
        this.poids = poids;
    }

    // Getters and setters
    public Long getCodeA() {
        return codeA;
    }

    public void setCodeA(Long codeA) {
        this.codeA = codeA;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Set<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(Set<Resultat> resultats) {
        this.resultats = resultats;
    }
}
