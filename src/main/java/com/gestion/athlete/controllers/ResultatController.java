package com.gestion.athlete.controllers;

import com.gestion.athlete.models.Resultat;
import com.gestion.athlete.models.ResultatId;
import com.gestion.athlete.services.ResultatService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultats")
public class ResultatController {

    private final ResultatService resultatService;

    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Resultat> createResultat(@RequestBody @Valid Resultat resultat) {
        Resultat savedResultat = resultatService.saveResultat(resultat);
        return ResponseEntity.ok(savedResultat);
    }

    @GetMapping
    public ResponseEntity<List<Resultat>> getAllResultats() {
        List<Resultat> resultats = resultatService.getAllResultats();
        return ResponseEntity.ok(resultats);
    }

    @GetMapping("/{athleteId}/{courseId}")
    public ResponseEntity<Resultat> getResultatById(@PathVariable Long athleteId, @PathVariable Long courseId) {
        ResultatId id = new ResultatId(athleteId, courseId);
        Optional<Resultat> resultat = resultatService.getResultatById(id);
        return resultat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{athleteId}/{courseId}")
    public ResponseEntity<Void> deleteResultatById(@PathVariable Long athleteId, @PathVariable Long courseId) {
        ResultatId id = new ResultatId(athleteId, courseId);
        resultatService.deleteResultatById(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{athleteId}/{courseId}")
    public ResponseEntity<Resultat> updateResultat(@PathVariable Long athleteId,
            @PathVariable Long courseId,
            @RequestBody @Valid Resultat newResultatData) {
        ResultatId id = new ResultatId(athleteId, courseId);
        Optional<Resultat> optionalResultat = resultatService.getResultatById(id);

        if (optionalResultat.isPresent()) {
            Resultat resultat = optionalResultat.get();
            resultat.setTemps(newResultatData.getTemps());
            Resultat updated = resultatService.saveResultat(resultat);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}