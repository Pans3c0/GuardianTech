package org.educastur.samuelepv59.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.educastur.samuelepv59.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    // Lista de docentes ordenado por sus apellidos
    List<Docente> findAllByOrderByApellidosAsc();

    // Lista de docentes de un departamento por nombre
    List<Docente> findByDepartamentoNombre(String nombreDepartamento);

    // Número de docentes de un departamento
    long countByDepartamentoCodigo(String codigo);
}
