package org.educastur.samuelepv59.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.educastur.samuelepv59.model.Asignatura;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer>  {

}
