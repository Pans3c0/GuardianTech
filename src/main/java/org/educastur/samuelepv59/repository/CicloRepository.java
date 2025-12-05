package org.educastur.samuelepv59.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.educastur.samuelepv59.model.Ciclo;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Integer> {

}