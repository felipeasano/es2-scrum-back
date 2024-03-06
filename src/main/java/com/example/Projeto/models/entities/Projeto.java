package com.example.Projeto.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nomeCliente;

    private String objetivo;

    private LocalDate inicio;

    private LocalDate fim;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "fk_time")
    private Time timeResponsavel;
}