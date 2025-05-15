package com.gestion.athlete.services;

import com.gestion.athlete.models.Compte;
import com.gestion.athlete.repositories.CompteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    private final CompteRepository compteRepository;
    private final PasswordEncoder passwordEncoder;

    public CompteService(CompteRepository compteRepository, PasswordEncoder passwordEncoder) {
        this.compteRepository = compteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Compte saveCompte(Compte compte) {
        compte.setPassword(passwordEncoder.encode(compte.getPassword()));
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

    public boolean passwordMatch(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    // ------------------------------
    
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

}