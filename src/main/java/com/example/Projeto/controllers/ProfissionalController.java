package com.example.Projeto.controllers;

import com.example.Projeto.models.entities.Especialidade;
import com.example.Projeto.models.entities.Profissional;
import com.example.Projeto.repositories.EspecialidadeRepository;
import com.example.Projeto.repositories.ProfissionalRepository;
import com.example.Projeto.repositories.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profissional")
@CrossOrigin
public class ProfissionalController {

    private final ProfissionalRepository profissionalRepository;
    private final EspecialidadeRepository especialidadeRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Profissional profissional) {
        Optional<Especialidade> optEspecialidade = especialidadeRepository.findByNome(profissional.getEspecialidade().getNome());
        optEspecialidade.ifPresent(profissional::setEspecialidade);

        Profissional saved = profissionalRepository.save(profissional);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Profissional> profissionais = profissionalRepository.findAll();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Profissional> profissional = profissionalRepository.findById(id);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Profissional profissional) {
        if (!profissionalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        profissional.setId(id);
        profissionalRepository.save(profissional);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!profissionalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        profissionalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
