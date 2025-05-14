package com.gestion.athlete.services;

import com.gestion.athlete.models.Compte;
import com.gestion.athlete.repositories.CompteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {

    private final CompteRepository compteRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CompteService(CompteRepository compteRepository, BCryptPasswordEncoder passwordEncoder) {
        this.compteRepository = compteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Compte saveCompte(Compte compte) {
        compte.setPassword(passwordEncoder.encode(compte.getPassword())); // Hash the password
        return compteRepository.save(compte);
    }

    public Optional<Compte> findByEmail(String email) {
        return compteRepository.findByEmail(email);
    }

    public Optional<Compte> findById(Long id) {
        return compteRepository.findById(id);
    }

    public void deleteById(Long id) {
        compteRepository.deleteById(id);
    }
}