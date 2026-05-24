package com.escolar.presenca.spe.repository;

import com.escolar.presenca.spe.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByTurmaLetra(String letra);
    List<Aluno> findByTurmaId(Long turmaId);
}
