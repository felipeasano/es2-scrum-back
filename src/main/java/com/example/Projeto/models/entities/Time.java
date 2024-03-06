package com.example.Projeto.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    private List<Profissional> integrantes;

    @OneToMany(mappedBy = "timeResponsavel")
    private List<Projeto> projetos;
}
