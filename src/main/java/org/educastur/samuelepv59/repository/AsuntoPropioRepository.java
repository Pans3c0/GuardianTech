package org.educastur.samuelepv59.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.educastur.samuelepv59.model.AsuntoPropio;

@Repository
public interface AsuntoPropioRepository extends JpaRepository<AsuntoPropio, Integer> {

}
