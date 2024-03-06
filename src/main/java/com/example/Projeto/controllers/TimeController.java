package com.example.Projeto.controllers;

import com.example.Projeto.models.entities.Profissional;
import com.example.Projeto.models.entities.Time;
import com.example.Projeto.repositories.ProfissionalRepository;
import com.example.Projeto.repositories.ProjetoRepository;
import com.example.Projeto.repositories.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time")
public class TimeController {

    private final TimeRepository timeRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ProjetoRepository projetoRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Time time) {
        Time savedTime = timeRepository.save(time);
        // Update Profissional objects with the saved Time
        for (Profissional profissional : time.getIntegrantes()) {
            profissionalRepository.save(profissional);
        }
        return ResponseEntity.ok(savedTime);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Time> times = timeRepository.findAll();
        // Optionally fetch related data using separate queries or lazy loading
        return ResponseEntity.ok(times);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Time> time = timeRepository.findById(id);
        // Optionally fetch related data using separate queries or lazy loading
        return time.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Time time) {
        // Check if Time with ID exists
        if (!timeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Update Time object
        Time updatedTime = timeRepository.save(time);
        // Update Profissional objects with the updated Time
        for (Profissional profissional : time.getIntegrantes()) {
            profissionalRepository.save(profissional);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // Check if Time with ID exists
        if (!timeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Handle cascading deletions or nulling out foreign keys (if applicable)
        timeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

