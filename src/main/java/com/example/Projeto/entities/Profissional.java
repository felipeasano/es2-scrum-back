package com.example.Projeto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    private Etnia etnia;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "fk_especialidade")
    private Especialidade especialidade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco")
    @JsonProperty("endereco")
    private Endereco endereco;
}
