package com.escolar.presenca.spe.controller;

import com.escolar.presenca.spe.dto.ChamadaRequest;
import com.escolar.presenca.spe.model.Aluno;
import com.escolar.presenca.spe.service.ChamadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chamada")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChamadaController {
    private final ChamadaService chamadaService;

    @GetMapping("/turma/{letra}")
    public ResponseEntity<List<Aluno>> getAlunosDaTurma(@PathVariable String letra) {
        return ResponseEntity.ok(chamadaService.getAlunoDaTurma(letra.toUpperCase()));
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarChamada(@RequestBody ChamadaRequest request) {
        chamadaService.salvarChamada(request);
        return ResponseEntity.ok(Map.of("mensagem", "Chamada salva com sucesso!"));
    }

    @GetMapping("/frequencia/{turmaLetra}")
    public ResponseEntity<?> getFrequencia(@PathVariable String turmaLetra) {
        return ResponseEntity.ok(chamadaService.getFrequencia(turmaLetra.toUpperCase()));
    }
}
