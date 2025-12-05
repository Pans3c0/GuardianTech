package org.educastur.samuelepv59.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.educastur.samuelepv59.model.Docente;
import org.educastur.samuelepv59.service.DocenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/docente")
@RequiredArgsConstructor
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/{id}")
    public Optional<Docente> buscar(@PathVariable Integer id) {
        return docenteService.buscarPorId(id);
    }

}
