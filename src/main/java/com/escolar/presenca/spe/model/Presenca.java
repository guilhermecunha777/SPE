package com.escolar.presenca.spe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "presencas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private boolean presente;

    @Column(nullable = false)
    private int quantidadeFaltas;
}
