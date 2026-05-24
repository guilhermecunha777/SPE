package com.escolar.presenca.spe.repository;

import com.escolar.presenca.spe.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    List<Presenca> findByAlunoId(Long alunoId);

    List<Presenca> findByAlunoTurmaLetra(String letra);

    boolean existsByAlunoIdAndData(Long alunoId, LocalDate data);

    @Query("SELECT COALESCE(SUM(p.quantidadeFaltas), 0) FROM Presenca p WHERE p.aluno.id = :alunoId")
    int totalFaltasPorAluno(@Param("alunoId") Long alunoId);
}