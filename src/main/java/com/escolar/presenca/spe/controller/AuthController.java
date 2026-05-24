package com.escolar.presenca.spe.controller;

import com.escolar.presenca.spe.model.Professor;
import com.escolar.presenca.spe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Optional<Professor> prof = authService.login(body.get("usuario"), body.get("senha"));

        if (prof.isPresent()) {
            Professor pf = prof.get();
            return ResponseEntity.ok(Map.of(
                    "sucesso", true,
                    "nome", pf.getNome(),
                    "usuario", pf.getUsuario()
            ));
        }
        return ResponseEntity.status(401).body(Map.of(
                "sucesso", false,
                "mensagem", "Usuário ou senha incorretos"
        ));
    }
}
