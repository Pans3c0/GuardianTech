package org.educastur.samuelepv59.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.educastur.samuelepv59.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {

}
