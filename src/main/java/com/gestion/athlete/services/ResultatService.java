package com.gestion.athlete.services;

import com.gestion.athlete.models.Resultat;
import com.gestion.athlete.models.ResultatId;
import com.gestion.athlete.repositories.ResultatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultatService {

    private final ResultatRepository resultatRepository;

    public ResultatService(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    public Resultat saveResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public List<Resultat> getAllResultats() {
        return resultatRepository.findAll();
    }

    public Optional<Resultat> getResultatById(ResultatId id) {
        return resultatRepository.findById(id);
    }

    public void deleteResultatById(ResultatId id) {
        resultatRepository.deleteById(id);
    }
}