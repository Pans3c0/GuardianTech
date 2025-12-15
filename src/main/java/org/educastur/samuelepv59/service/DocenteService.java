package org.educastur.samuelepv59.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.educastur.samuelepv59.dto.DocenteCreateDTO;
import org.educastur.samuelepv59.dto.DocenteDTO;
import org.educastur.samuelepv59.dto.AsuntoPropioDTO;
import org.educastur.samuelepv59.model.AsuntoPropio;
import org.educastur.samuelepv59.model.Departamento;
import org.educastur.samuelepv59.model.Docente;
import org.educastur.samuelepv59.repository.AsuntoPropioRepository;
import org.educastur.samuelepv59.repository.DepartamentoRepository;
import org.educastur.samuelepv59.repository.DocenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final DepartamentoRepository departamentoRepository;
    private final AsuntoPropioRepository asuntoPropioRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Optional<DocenteDTO> buscarPorId(Integer id) {
        return docenteRepository.findById(id)
                .map(docente -> modelMapper.map(docente, DocenteDTO.class));
    }

    @Transactional
    public DocenteDTO crearDocente(DocenteCreateDTO docenteDTO) {
        Docente docente = modelMapper.map(docenteDTO, Docente.class);
        Departamento departamento = departamentoRepository.findById(docenteDTO.getIdDepartamento())
                .orElseThrow(() -> new RuntimeException("Error: Departamento no encontrado."));
        docente.setDepartamento(departamento);
        Docente docenteGuardado = docenteRepository.save(docente);
        return modelMapper.map(docenteGuardado, DocenteDTO.class);
    }

    @Transactional(readOnly = true)
    public Boolean puedePedirDiaPropio(Integer docenteId) {
        LocalDate hoy = LocalDate.now();
        int month = hoy.getMonthValue();
        LocalDate inicioTrimestre;
        LocalDate finTrimestre;

        // 1er Trimestre: Septiembre - Diciembre
        if (month >= 9 && month <= 12) {
            inicioTrimestre = LocalDate.of(hoy.getYear(), 9, 1);
            finTrimestre = LocalDate.of(hoy.getYear(), 12, 31);
        } else if (month >= 1 && month <= 3) {
            // 2do Trimestre: Enero - Marzo
            inicioTrimestre = LocalDate.of(hoy.getYear(), 1, 1);
            finTrimestre = LocalDate.of(hoy.getYear(), 3, 31);
        } else if (month >= 4 && month <= 6) {
            // 3er Trimestre: Abril - Junio
            inicioTrimestre = LocalDate.of(hoy.getYear(), 4, 1);
            finTrimestre = LocalDate.of(hoy.getYear(), 6, 30);
        } else {
            // Fuera de curso
            return false;
        }

        long dias = asuntoPropioRepository.countDiasDisfrutados(docenteId, inicioTrimestre, finTrimestre);
        return dias == 0;
    }

    @Transactional
    public boolean solicitarDiaPropio(Integer docenteId, LocalDate fechaSolicitada, String descripcion) {
        if (fechaSolicitada == null)
            return false;
        Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
        if (docenteOpt.isPresent()) {
            Docente docente = docenteOpt.get();
            AsuntoPropio asunto = new AsuntoPropio();
            asunto.setDocente(docente);
            asunto.setDiaSolicitado(fechaSolicitada);
            asunto.setFechaTramitacion(LocalDateTime.now());
            if (descripcion != null) {
                asunto.setDescripcion(descripcion);
            } else {
                asunto.setDescripcion("Solicitud de día propio");
            }
            asunto.setAprobado(null);
            asuntoPropioRepository.save(asunto);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean validarAsuntoPropio(Integer idAsunto, boolean decision) {
        if (idAsunto == null)
            return false;
        Optional<AsuntoPropio> asuntoOpt = asuntoPropioRepository.findById(idAsunto);
        if (asuntoOpt.isPresent()) {
            AsuntoPropio asunto = asuntoOpt.get();
            asunto.setAprobado(decision);
            asunto.setFechaTramitacion(LocalDateTime.now());
            asuntoPropioRepository.save(asunto);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<AsuntoPropioDTO> listarAsuntosPropios(Integer docenteId) {
        if (docenteId == null)
            return List.of();
        return asuntoPropioRepository.findByDocenteId(docenteId).stream()
                .map(asunto -> {
                    AsuntoPropioDTO dto = modelMapper.map(asunto, AsuntoPropioDTO.class);
                    if (asunto.getDocente() != null) {
                        dto.setNombreDocente(
                                asunto.getDocente().getNombre() + " " + asunto.getDocente().getApellidos());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // --- Consultas Docentes ---

    @Transactional(readOnly = true)
    public List<DocenteDTO> listarDocentesPorApellido() {
        return docenteRepository.findAllByOrderByApellidosAsc().stream()
                .map(d -> modelMapper.map(d, DocenteDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DocenteDTO> listarDocentesPorDepartamento(String nombre) {
        return docenteRepository.findByDepartamentoNombre(nombre).stream()
                .map(d -> modelMapper.map(d, DocenteDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long contarProfesoresDepartamento(String codigo) {
        return docenteRepository.countByDepartamentoCodigo(codigo);
    }

    // --- Consultas Avanzadas Asuntos ---

    @Transactional(readOnly = true)
    public List<AsuntoPropioDTO> listarPendientesDeDisfrutar() {
        return asuntoPropioRepository.findPendientesDeDisfrutar().stream()
                .map(a -> {
                    AsuntoPropioDTO dto = modelMapper.map(a, AsuntoPropioDTO.class);
                    if (a.getDocente() != null) {
                        dto.setNombreDocente(a.getDocente().getNombre() + " " + a.getDocente().getApellidos());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<DocenteDTO> buscarDocenteMasDias() {
        return asuntoPropioRepository.findDocenteConMasDias()
                .map(d -> modelMapper.map(d, DocenteDTO.class));
    }

}