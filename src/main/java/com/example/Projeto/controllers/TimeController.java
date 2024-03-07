package com.example.Projeto.controllers;

import com.example.Projeto.entities.Profissional;
import com.example.Projeto.entities.Time;
import com.example.Projeto.repositories.ProfissionalRepository;
import com.example.Projeto.repositories.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time")
@CrossOrigin
public class TimeController {

    private final TimeRepository timeRepository;
    private final ProfissionalRepository profissionalRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Time time) {
        Time savedTime = timeRepository.save(time);
        return ResponseEntity.ok(savedTime);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Time> times = timeRepository.findAll();
        return ResponseEntity.ok(times);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Time> time = timeRepository.findById(id);
        return time.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{timeId}/integrantes/{profissionalId}")
    public ResponseEntity<?> addIntegrante(@PathVariable Long timeId, @PathVariable Long profissionalId) {
        Optional<Time> timeOptional = timeRepository.findById(timeId);
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(profissionalId);

        if (!timeOptional.isPresent() || !profissionalOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Time time = timeOptional.get();
        Profissional profissional = profissionalOptional.get();

        time.getIntegrantes().add(profissional);
        timeRepository.save(time);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{timeId}/integrantes/{profissionalId}")
    public ResponseEntity<?> removeIntegrante(@PathVariable Long timeId, @PathVariable Long profissionalId) {
        Optional<Time> timeOptional = timeRepository.findById(timeId);
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(profissionalId);

        if (!timeOptional.isPresent() || !profissionalOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Time time = timeOptional.get();
        Profissional profissional = profissionalOptional.get();

        time.getIntegrantes().remove(profissional);
        timeRepository.save(time);

        profissionalRepository.save(profissional);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Time time) {
        Optional<Time> optTime = timeRepository.findById(id);
        if (optTime.isEmpty())
            return ResponseEntity.notFound().build();

        optTime.get().setNome(time.getNome());
        Time updatedTime = timeRepository.save(optTime.get());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!timeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        timeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}