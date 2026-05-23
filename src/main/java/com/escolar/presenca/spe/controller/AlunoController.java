package com.escolar.presenca.spe.controller;

import com.escolar.presenca.spe.model.Aluno;
import com.escolar.presenca.spe.repository.AlunoRepository;
import com.escolar.presenca.spe.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/turma/{id}")
    public List<Aluno> listar(@PathVariable long id){
        return alunoService.listarTurma(id);
    }
}
