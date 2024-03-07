package com.example.Projeto.repositories;

import com.example.Projeto.models.entities.Especialidade;
import com.example.Projeto.models.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    Optional<Especialidade> findByNome(String nome);
}
