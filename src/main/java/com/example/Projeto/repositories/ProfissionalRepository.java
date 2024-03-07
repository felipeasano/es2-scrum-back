package com.example.Projeto.repositories;

import com.example.Projeto.entities.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
