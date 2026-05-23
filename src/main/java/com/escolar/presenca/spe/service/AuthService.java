package com.escolar.presenca.spe.service;

import com.escolar.presenca.spe.model.Professor;
import com.escolar.presenca.spe.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfessorRepository professorRepository;
    public Optional<Professor> login(String usuario,String senha) {
        return professorRepository.findByUsuarioAndSenha(usuario,senha);
    }
}
