package com.gestion.athlete.repositories;

import com.gestion.athlete.models.Resultat;
import com.gestion.athlete.models.ResultatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultatRepository extends JpaRepository<Resultat, ResultatId> {
    // Add custom query methods if needed
}