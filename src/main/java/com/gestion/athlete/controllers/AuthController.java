package com.gestion.athlete.controllers;

import com.gestion.athlete.dto.LoginRequest;
import com.gestion.athlete.dto.RegisterRequest;
import com.gestion.athlete.models.Compte;
import com.gestion.athlete.services.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class AuthController {

    private final CompteService compteService;
private final PasswordEncoder passwordEncoder;

// In constructor:
public AuthController(CompteService compteService, PasswordEncoder passwordEncoder) {
    this.compteService = compteService;
    this.passwordEncoder = passwordEncoder;
}


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        String role = "USER";
        String encodedPassword = passwordEncoder.encode(req.password);
        if (!"ADMIN".equalsIgnoreCase(req.role) && !"USER".equalsIgnoreCase(req.role)) {
            return ResponseEntity.badRequest().body("Rôle invalide. Utilisez 'USER' ou 'ADMIN'.");
        }
        role = req.role.toUpperCase();
        
    
        Compte compte = new Compte(
                req.username,
                req.email,
                encodedPassword,
                role
        );
    
        Compte saved = compteService.saveCompte(compte);
    
        if (saved == null) {
            return ResponseEntity.badRequest().body("Erreur lors de la création de l'utilisateur");
        }
    
        return ResponseEntity.ok(
                "Utilisateur créé avec l'ID " + saved.getId() + " et le rôle " + role);
    }
    

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Compte compte = compteService.findByEmail(req.email).orElse(null);

        if (compte == null) {
            return ResponseEntity.status(404).body("Utilisateur non trouvé");
        }

        if (!compteService.passwordMatch(req.password, compte.getPassword())) {
            return ResponseEntity.status(401).body("Mot de passe incorrect");
        }

        return ResponseEntity.ok("Connexion réussie pour l'utilisateur : " + compte.getUsername());
    }

}
