package com.example.Projeto.repositories;

import com.example.Projeto.models.entities.Especialidade;
import com.example.Projeto.models.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
