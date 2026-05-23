package com.escolar.presenca.spe.dto;

import com.escolar.presenca.spe.model.Aluno;
import lombok.Data;

import java.util.List;

@Data
public class ChamadaRequest {
    private String turmaLetra;
    private int faltasporAusencia;
    private List<RegistroPresenca> registros;

    @Data
    public static class RegistroPresenca {
        private Long alunoId;
        private boolean presenca;
        private int quantidadeFaltas;
    }
}
