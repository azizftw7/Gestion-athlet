package com.gestion.athlete.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Resultat {

    @EmbeddedId
    private ResultatId id;

    @NotNull(message = "Le temps est obligatoire")
    @Min(value = 0, message = "Le temps doit être un nombre positif")
    private Double temps;

    @ManyToOne
    @MapsId("codeA")
    @JoinColumn(name = "codeA")
    private Athlete athlete;

    @ManyToOne
    @MapsId("numCourse")
    @JoinColumn(name = "numCourse")
    private Course course;

    public Resultat() {
    }

    public ResultatId getId() {
        return id;
    }

    public void setId(ResultatId id) {
        this.id = id;
    }

    public double getTemps() {
        return temps;
    }

    public void setTemps(double temps) {
        this.temps = temps;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
