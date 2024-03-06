package com.example.Projeto.repositories;

import com.example.Projeto.models.entities.Profissional;
import com.example.Projeto.models.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
