package com.escolar.presenca.spe.service;

import com.escolar.presenca.spe.model.Aluno;
import com.escolar.presenca.spe.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTurma(long turmaID) {
        return alunoRepository.findByTurmaId(turmaID);
    }
}
