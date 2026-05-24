package com.escolar.presenca.spe.service;

import com.escolar.presenca.spe.dto.ChamadaRequest;
import com.escolar.presenca.spe.dto.FrequenciaResponse;
import com.escolar.presenca.spe.model.Aluno;
import com.escolar.presenca.spe.model.Presenca;
import com.escolar.presenca.spe.model.Turma;
import com.escolar.presenca.spe.repository.AlunoRepository;
import com.escolar.presenca.spe.repository.PresencaRepository;
import com.escolar.presenca.spe.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChamadaService {

    private final PresencaRepository presencaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    private static final int TOTAL_DIAS_SEMESTRE = 100;

    @Transactional
    public void salvarChamada(ChamadaRequest request) {
        LocalDate dataAtualizacao = LocalDate.now();
        for (ChamadaRequest.RegistroPresenca reg : request.getRegistros()) {
            Aluno aluno = alunoRepository.findById(reg.getAlunoId()).orElseThrow(() -> new RuntimeException("aluno nao encontrado: " + reg.getAlunoId()));

            if (presencaRepository.existsByAlunoIdAndData(aluno.getId(), dataAtualizacao)) continue;

            Presenca presenca = Presenca.builder()
                    .aluno(aluno)
                    .data(dataAtualizacao)
                    .presente(reg.isPresenca())
                    .quantidadeFaltas(reg.isPresenca() ? 0 : reg.getQuantidadeFaltas())
                    .build();

            presencaRepository.save(presenca);
        }
    }

    public List<FrequenciaResponse> getFrequencia(String turmaLetra) {
        Turma turma = turmaRepository.findByLetra(turmaLetra).orElseThrow(() -> new RuntimeException("turma nao encontrada: " + turmaLetra));
        return alunoRepository.findByTurmaLetra(turmaLetra).stream().map(aluno -> {
            int totalFaltas = presencaRepository.totalFaltasPorAluno(aluno.getId());
            int totalAulas = turma.getAulasDia() * TOTAL_DIAS_SEMESTRE;
            double freq = Math.max(0, ((double) (totalAulas - totalFaltas) / totalAulas) * 100);
            freq = Math.round(freq * 10.0) / 10.0;

            String status = freq >= 75 ? "regular" : freq >= 50 ? "atenção" : "Critico";

            return new FrequenciaResponse(aluno.getId(), aluno.getNome(), turmaLetra, totalFaltas, totalAulas, freq, status);
        }).collect(Collectors.toList());
    }
    public List<Aluno> getAlunoDaTurma(String turmaLetra) {
        return alunoRepository.findByTurmaLetra(turmaLetra);
    }
}
