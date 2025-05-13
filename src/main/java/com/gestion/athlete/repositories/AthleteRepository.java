package com.gestion.athlete.repositories;

import com.gestion.athlete.models.athlet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<athlet, Long> {
  
}
