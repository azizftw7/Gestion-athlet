package com.gestion.athlete.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numCourse;

    @NotBlank(message = "Le lieu est obligatoire")
    private String lieu;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    @NotNull(message = "La date est obligatoire")
    private LocalDate dateCourse;

    // Getters and setters

    public Long getNumCourse() {
        return numCourse;
    }

    public void setNumCourse(Long numCourse) {
        this.numCourse = numCourse;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public LocalDate getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(LocalDate dateCourse) {
        this.dateCourse = dateCourse;
    }
}
