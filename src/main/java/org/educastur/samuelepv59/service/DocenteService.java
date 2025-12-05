package org.educastur.samuelepv59.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.educastur.samuelepv59.model.Docente;
import org.educastur.samuelepv59.repository.DocenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    public Optional<Docente> buscarPorId(Integer id) {
        return docenteRepository.findById(id);
    }

    // devuelve true si NO tiene día propio en la lista (es decir, puede solicitar
    // día propio)
    public Boolean diaPropioTrimestre(Integer docenteId) {
        Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
        if (docenteOpt.isPresent()) {
            Docente docente = docenteOpt.get();
            return docente.getAsuntosPropios().isEmpty();
        }
        return false;
    }

}
