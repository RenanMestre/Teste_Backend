package com.api.Teste;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmailAndPassword(
                loginRequest.getEmail(), loginRequest.getPassword());

        if (user.isPresent()) {
            String token = jwtUtil.generateToken(user.get().getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    // Exemplo de rota protegida
    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (jwtUtil.isTokenValid(token)) {
            String email = jwtUtil.extractEmail(token);
            return ResponseEntity.ok("Olá, " + email + "! Você acessou uma rota protegida.");
        } else {
            return ResponseEntity.status(403).body("Token inválido");
        }
    }
}
