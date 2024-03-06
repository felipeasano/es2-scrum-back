package com.example.Projeto.controllers;

import com.example.Projeto.repositories.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projeto")
public class ProjetoController {

    private final ProjetoRepository projetoRepository;


}
