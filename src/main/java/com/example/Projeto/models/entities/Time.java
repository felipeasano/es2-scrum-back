package com.example.Projeto.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    private List<Profissional> integrantes = new ArrayList<>();

    @OneToMany(mappedBy = "timeResponsavel")
    private List<Projeto> projetos = new ArrayList<>();

}
