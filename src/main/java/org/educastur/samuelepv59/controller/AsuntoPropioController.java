package org.educastur.samuelepv59.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.educastur.samuelepv59.dto.AsuntoPropioDTO;
import org.educastur.samuelepv59.dto.AsuntoPropioResolucionDTO;
import org.educastur.samuelepv59.dto.AsuntoPropioSolicitudDTO;
import org.educastur.samuelepv59.service.DocenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asuntos-propios")
@RequiredArgsConstructor
public class AsuntoPropioController {

    private final DocenteService docenteService;

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitar(@RequestBody AsuntoPropioSolicitudDTO solicitud) {
        boolean exito = docenteService.solicitarDiaPropio(
                solicitud.getIdDocente(),
                solicitud.getDiaSolicitado(),
                solicitud.getDescripcion());

        if (exito) {
            return ResponseEntity.ok("Solicitud registrada correctamente.");
        } else {
            return ResponseEntity.badRequest()
                    .body("No se pudo registrar la solicitud (Docente no encontrado o fecha inválida).");
        }
    }

    @PutMapping("/{idAsunto}/validar")
    public ResponseEntity<String> validar(@PathVariable Integer idAsunto,
            @RequestBody AsuntoPropioResolucionDTO resolucion) {
        boolean exito = docenteService.validarAsuntoPropio(idAsunto, resolucion.getAprobado());

        if (exito) {
            return ResponseEntity.ok("Asunto propio actualizado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/docente/{idDocente}")
    public ResponseEntity<List<AsuntoPropioDTO>> listarPorDocente(@PathVariable Integer idDocente) {
        List<AsuntoPropioDTO> lista = docenteService.listarAsuntosPropios(idDocente);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<AsuntoPropioDTO>> listarPendientes() {
        return ResponseEntity.ok(docenteService.listarPendientesDeDisfrutar());
    }

    @GetMapping("/docente-max-dias")
    public ResponseEntity<org.educastur.samuelepv59.dto.DocenteDTO> docenteMaxDias() {
        return docenteService.buscarDocenteMasDias()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
