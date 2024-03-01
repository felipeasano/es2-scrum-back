package com.example.Projeto.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // endereco

    private String nome;

    private LocalDate nascimento;

    private String genero;

    private String raca;

    // especialidade
}
