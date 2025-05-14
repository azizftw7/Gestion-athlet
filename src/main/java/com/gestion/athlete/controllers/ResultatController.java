package com.gestion.athlete.controllers;

import com.gestion.athlete.models.Resultat;
import com.gestion.athlete.models.ResultatId;
import com.gestion.athlete.services.ResultatService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Resultat> createResultat(@RequestBody Resultat resultat) {
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

    @DeleteMapping("/{athleteId}/{courseId}")
    public ResponseEntity<Void> deleteResultatById(@PathVariable Long athleteId, @PathVariable Long courseId) {
        ResultatId id = new ResultatId(athleteId, courseId);
        resultatService.deleteResultatById(id);
        return ResponseEntity.noContent().build();
    }
}