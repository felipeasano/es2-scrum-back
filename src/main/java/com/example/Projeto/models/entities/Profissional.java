package com.example.Projeto.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    private Etnia etnia;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "fk_especialidade")
    private Especialidade especialidade;

    @ManyToMany
    private List<Time> times;
}
