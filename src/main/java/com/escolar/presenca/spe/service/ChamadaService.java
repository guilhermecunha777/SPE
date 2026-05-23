package com.escolar.presenca.spe.service;

import com.escolar.presenca.spe.dto.ChamadaRequest;
import com.escolar.presenca.spe.model.Aluno;
import com.escolar.presenca.spe.model.Presenca;
import com.escolar.presenca.spe.repository.AlunoRepository;
import com.escolar.presenca.spe.repository.PresencaRepository;
import com.escolar.presenca.spe.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ChamadaService {
    private final PresencaRepository presencaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    private static final int TOTAL_DIAS_SEMESTRE = 100;

    @Transactional
    public void salvarChamada(ChamadaRequest request){
        LocalDate dataAtualizacao = LocalDate.now();
        for (ChamadaRequest.RegistroPresenca reg : request.getRegistros()) {
            Aluno aluno = alunoRepository.findBy
        }
    }
}
