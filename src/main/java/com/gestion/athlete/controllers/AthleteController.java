package com.gestion.athlete.controllers;

import com.gestion.athlete.models.Athlete;
import com.gestion.athlete.services.AthleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Athlete> createAthlete(@Valid @RequestBody Athlete athlete) {
        Athlete savedAthlete = athleteService.saveAthlete(athlete);
        return ResponseEntity.ok(savedAthlete);
    }

    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        return ResponseEntity.ok(athletes);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Long id) {
        Optional<Athlete> athlete = athleteService.getAthleteById(id);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthleteById(@PathVariable Long id) {
        athleteService.deleteAthleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable Long id, @Valid @RequestBody Athlete athleteDetails) {
        Optional<Athlete> optionalAthlete = athleteService.getAthleteById(id);
        if (!optionalAthlete.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Athlete athleteToUpdate = optionalAthlete.get();
        athleteToUpdate.setNomPrenom(athleteDetails.getNomPrenom());
        athleteToUpdate.setGenre(athleteDetails.getGenre());
        athleteToUpdate.setTaille(athleteDetails.getTaille());
        athleteToUpdate.setPoids(athleteDetails.getPoids());

        Athlete updatedAthlete = athleteService.saveAthlete(athleteToUpdate);
        return ResponseEntity.ok(updatedAthlete);
    }
}
