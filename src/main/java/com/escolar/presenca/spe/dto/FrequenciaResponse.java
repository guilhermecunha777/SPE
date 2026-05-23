package com.escolar.presenca.spe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FrequenciaResponse {
    private Long alunoId;
    private String alunoNome;
    private String turma;
    private int totalFaltas;
    private int totalAlunasPossiveis;
    private double frequenciaPercent;
    private String status;
}
