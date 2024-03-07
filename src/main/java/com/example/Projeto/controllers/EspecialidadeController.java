package com.example.Projeto.controllers;

import com.example.Projeto.entities.Especialidade;
import com.example.Projeto.repositories.EspecialidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/especialidade")
@CrossOrigin
public class EspecialidadeController {

    private final EspecialidadeRepository especialidadeRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Especialidade especialidade) {
        Especialidade saved = especialidadeRepository.save(especialidade);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Especialidade> especialidades = especialidadeRepository.findAll();
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Especialidade> especialidade = especialidadeRepository.findById(id);
        return especialidade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Especialidade especialidade) {
        if (!especialidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        especialidade.setId(id);
        especialidadeRepository.save(especialidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!especialidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        especialidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
