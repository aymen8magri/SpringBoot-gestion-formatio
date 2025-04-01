package com.formation.gestion_formation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.gestion_formation.services.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/loginEntreprise")
    public ResponseEntity<?> loginEntreprise(@RequestBody LoginRequest request) {
         try {
            String token = authService.authenticateEntreprise(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect !");
        }
    }
    @PostMapping("/loginStagiaire")
    public ResponseEntity<?> loginStagiaire(@RequestBody LoginRequest request) {
         try {
            String token = authService.authenticateStagiaire(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect !");
        }
    }
}

class LoginRequest {
    private String email;
    private String password;
    
    // Getters et Setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
