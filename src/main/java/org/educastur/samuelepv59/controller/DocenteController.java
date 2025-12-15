package org.educastur.samuelepv59.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.educastur.samuelepv59.dto.DocenteCreateDTO;
import org.educastur.samuelepv59.dto.DocenteDTO;
import org.educastur.samuelepv59.service.DocenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/docente")
@RequiredArgsConstructor
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> buscar(@PathVariable Integer id) {
        Optional<DocenteDTO> docenteOpt = docenteService.buscarPorId(id);
        if (docenteOpt.isPresent()) {
            return ResponseEntity.ok(docenteOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> crear(@RequestBody DocenteCreateDTO docenteDTO) {
        DocenteDTO nuevoDocente = docenteService.crearDocente(docenteDTO);
        return new ResponseEntity<>(nuevoDocente, HttpStatus.CREATED);
    }

    // Listar por apellidos
    @GetMapping("/listar")
    public ResponseEntity<List<DocenteDTO>> listarTodos() {
        return ResponseEntity.ok(docenteService.listarDocentesPorApellido());
    }

    // Listar por departamento
    @GetMapping("/departamento/{nombre}")
    public ResponseEntity<List<DocenteDTO>> listarPorDepto(@PathVariable String nombre) {
        return ResponseEntity.ok(docenteService.listarDocentesPorDepartamento(nombre));
    }

    // Contar por departamento
    @GetMapping("/departamento/{codigo}/count")
    public ResponseEntity<Long> contarPorDepto(@PathVariable String codigo) {
        return ResponseEntity.ok(docenteService.contarProfesoresDepartamento(codigo));
    }
}
