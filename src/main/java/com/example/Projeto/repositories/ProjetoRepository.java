package com.example.Projeto.repositories;

import com.example.Projeto.models.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
