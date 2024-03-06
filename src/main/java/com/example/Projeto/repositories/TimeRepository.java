package com.example.Projeto.repositories;

import com.example.Projeto.models.entities.Projeto;
import com.example.Projeto.models.entities.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
