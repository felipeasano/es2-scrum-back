package com.example.Projeto.controllers;

import com.example.Projeto.entities.Projeto;
import com.example.Projeto.entities.Time;
import com.example.Projeto.repositories.ProjetoRepository;
import com.example.Projeto.repositories.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projeto")
@RequiredArgsConstructor
@CrossOrigin
public class ProjetoController {

    private final ProjetoRepository projetoRepository;
    private final TimeRepository timeRepository;

    @GetMapping
    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable Long id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);
        return projetoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Projeto projeto) {
        if (projeto.getTimeResponsavel() == null || projeto.getTimeResponsavel().getId() == null)
            return ResponseEntity.badRequest().body("Time responsável não pode ser nulo");

        Time time = timeRepository.findById(projeto.getTimeResponsavel().getId()).orElse(null);
        if (time == null)
            return ResponseEntity.notFound().build();

        projeto.setTimeResponsavel(time);
        return ResponseEntity.ok(projetoRepository.save(projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Projeto projeto) {
        if (projeto.getTimeResponsavel() == null || projeto.getTimeResponsavel().getId() == null) {
            return ResponseEntity.badRequest().body("Time responsável não pode ser nulo");
        }
        Time time = timeRepository.findById(projeto.getTimeResponsavel().getId()).orElse(null);
        if (time == null) {
            return ResponseEntity.notFound().build();
        }
        Projeto projetoEncontrado = projetoRepository.findById(id).orElse(null);
        if (projetoEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        projetoEncontrado.setNome(projeto.getNome());
        projetoEncontrado.setNomeCliente(projeto.getNomeCliente());
        projetoEncontrado.setObjetivo(projeto.getObjetivo());
        projetoEncontrado.setInicio(projeto.getInicio());
        projetoEncontrado.setFim(projeto.getFim());
        projetoEncontrado.setValor(projeto.getValor());
        projetoEncontrado.setTimeResponsavel(time);
        return ResponseEntity.ok(projetoRepository.save(projetoEncontrado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Projeto projetoEncontrado = projetoRepository.findById(id).orElse(null);
        if (projetoEncontrado == null)
            return ResponseEntity.notFound().build();
        projetoRepository.delete(projetoEncontrado);
        return ResponseEntity.noContent().build();
    }
}
