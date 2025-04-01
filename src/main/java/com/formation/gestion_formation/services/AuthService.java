package com.formation.gestion_formation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.formation.gestion_formation.entities.Entreprise;
import com.formation.gestion_formation.entities.Stagiaire;
import com.formation.gestion_formation.repositories.EntrepriseRepository;
import com.formation.gestion_formation.repositories.StagiaireRepository;

@Service
public class AuthService {
    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateEntreprise(String email, String password) {
        Optional<Entreprise> entreprise = entrepriseRepository.findByEmail(email);
        if (entreprise.isPresent() && passwordEncoder.matches(password, entreprise.get().getPassword())) {
            return JwtUtil.generateToken(entreprise.get().getId(), email, "ENTREPRISE");
        }

        throw new UsernameNotFoundException("ENTREPRISE non trouvé");
    }
    public String authenticateStagiaire(String email, String password) {
        Optional<Stagiaire> stagiaire = stagiaireRepository.findByEmail(email);
        if (stagiaire.isPresent() && passwordEncoder.matches(password, stagiaire.get().getPassword())) {
            return JwtUtil.generateToken(stagiaire.get().getId(), email, "STAGIAIRE");
        }
        
        throw new UsernameNotFoundException("STAGIAIRE non trouvé");
    }

}
