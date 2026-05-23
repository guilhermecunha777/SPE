package com.escolar.presenca.spe.repository;

import com.escolar.presenca.spe.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByUsuarioAndSenha(String usuario, String senha);
}
