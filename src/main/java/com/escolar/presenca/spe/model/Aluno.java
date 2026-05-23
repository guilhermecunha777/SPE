package com.escolar.presenca.spe.model;

import jakarta.persistence.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @ManyToOne
    private Turma turma;
}
