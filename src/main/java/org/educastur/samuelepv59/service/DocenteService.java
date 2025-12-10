package org.educastur.samuelepv59.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.educastur.samuelepv59.dto.DocenteCreateDTO;
import org.educastur.samuelepv59.dto.DocenteDTO;
import org.educastur.samuelepv59.model.Departamento;
import org.educastur.samuelepv59.model.Docente;
import org.educastur.samuelepv59.repository.DepartamentoRepository;
import org.educastur.samuelepv59.repository.DocenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;
    
    // Inyectamos las dependencias que vamos a necesitar
    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public Optional<DocenteDTO> buscarPorId(Integer id) {
        // Busca la entidad y si la encuentra, la mapea a un DTO
        return docenteRepository.findById(id)
                .map(docente -> modelMapper.map(docente, DocenteDTO.class));
    }
    
    public DocenteDTO crearDocente(DocenteCreateDTO docenteDTO) {
        // Usamos ModelMapper para mapear los campos simples (nombre, email, etc.)
        Docente docente = modelMapper.map(docenteDTO, Docente.class);

        // Buscamos la entidad Departamento usando el ID que nos llega en el DTO
        Departamento departamento = departamentoRepository.findById(docenteDTO.getIdDepartamento())
                .orElseThrow(() -> new RuntimeException("Error: Departamento no encontrado."));
        
        // Asignamos la entidad completa al docente
        docente.setDepartamento(departamento);

        // Guardamos la nueva entidad Docente en la BBDD
        Docente docenteGuardado = docenteRepository.save(docente);
        
        // Mapeamos la entidad guardada a un DTO para devolverla al controlador
        return modelMapper.map(docenteGuardado, DocenteDTO.class);
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
