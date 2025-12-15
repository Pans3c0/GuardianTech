package org.educastur.samuelepv59.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.educastur.samuelepv59.model.AsuntoPropio;
import org.educastur.samuelepv59.model.Docente;

@Repository
public interface AsuntoPropioRepository extends JpaRepository<AsuntoPropio, Integer> {
    List<AsuntoPropio> findByDocenteId(Integer idDocente);

    // 1. Días pendientes de disfrutar (Aprobados y fecha futura)
    @Query("SELECT a FROM AsuntoPropio a WHERE a.aprobado = true AND a.diaSolicitado > CURRENT_DATE")
    List<AsuntoPropio> findPendientesDeDisfrutar();

    // 2. Contar días disfrutados por docente en un rango (para la validación del
    // trimestre)
    @Query("SELECT COUNT(a) FROM AsuntoPropio a WHERE a.docente.id = :idDocente AND a.aprobado = true AND a.diaSolicitado BETWEEN :inicio AND :fin")
    long countDiasDisfrutados(Integer idDocente, LocalDate inicio, LocalDate fin);

    // 3. Docente con más días disfrutados (Aprobados y fecha pasada)
    // Esta es compleja, devuelve un array de objetos o el ID del docente.
    @Query("SELECT a.docente FROM AsuntoPropio a WHERE a.aprobado = true AND a.diaSolicitado < CURRENT_DATE GROUP BY a.docente ORDER BY COUNT(a) DESC LIMIT 1")
    Optional<Docente> findDocenteConMasDias();
}
