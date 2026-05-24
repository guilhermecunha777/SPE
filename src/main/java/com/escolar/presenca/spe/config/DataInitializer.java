package com.escolar.presenca.spe.config;

import com.escolar.presenca.spe.model.Aluno;
import com.escolar.presenca.spe.model.Professor;
import com.escolar.presenca.spe.model.Turma;
import com.escolar.presenca.spe.repository.AlunoRepository;
import com.escolar.presenca.spe.repository.ProfessorRepository;
import com.escolar.presenca.spe.repository.TurmaRepository;
import com.escolar.presenca.spe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public void run(String... args) {
        if (turmaRepository.count() > 0) return;

        Turma turmaA = turmaRepository.save(Turma.builder().letra("A").aulasDia(4).build());
        Turma turmaB = turmaRepository.save(Turma.builder().letra("B").aulasDia(1).build());
        Turma turmaC = turmaRepository.save(Turma.builder().letra("C").aulasDia(2).build());

        List.of("Ana Beatriz Silva", "Bruno Oliveira", "Carla Mendes", "Diego Ferreira", "Eduarda Costa", "Felipe Rocha", "Gabriela Lima")
                .forEach(nome -> alunoRepository.save(Aluno.builder().nome(nome).turma(turmaA).build()));

        List.of("Henrique Souza", "Isabela Martins", "João Pedro Alves", "Karina Pereira", "Lucas Araújo", "Mariana Santos", "Nicolas Gomes")
                .forEach(nome -> alunoRepository.save(Aluno.builder().nome(nome).turma(turmaB).build()));

        List.of("Olivia Ribeiro", "Pedro Carvalho", "Quiteria Nunes", "Rafael Dias", "Sabrina Moura", "Thiago Barros", "Ursula Vieira")
                .forEach(nome -> alunoRepository.save(Aluno.builder().nome(nome).turma(turmaC).build()));

        professorRepository.save(Professor.builder().usuario("professor").senha("senha123").nome("prof. Jython").build());

        System.out.println("dados inicializados com sucesso");
    }
}
