package com.gestion.athlete.controllers;

import com.gestion.athlete.models.Athlete;
import com.gestion.athlete.services.AthleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @PostMapping
    public ResponseEntity<Athlete> createAthlete(@RequestBody Athlete athlete) {
        Athlete savedAthlete = athleteService.saveAthlete(athlete);
        return ResponseEntity.ok(savedAthlete);
    }

    @GetMapping
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Long id) {
        Optional<Athlete> athlete = athleteService.getAthleteById(id);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthleteById(@PathVariable Long id) {
        athleteService.deleteAthleteById(id);
        return ResponseEntity.noContent().build();
    }
}