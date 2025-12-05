package org.educastur.samuelepv59.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.educastur.samuelepv59.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {

}
