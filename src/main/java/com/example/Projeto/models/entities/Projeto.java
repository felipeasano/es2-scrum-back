package com.example.Projeto.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cliente;

    private String objetivo;

    private LocalDate inicio;

    private LocalDate fim;

    private float valor;

    //time
}
