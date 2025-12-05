package org.educastur.samuelepv59.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ciclos")
public class Ciclo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciclo")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "familia")
    private String familia;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "turno")
    private String turno;

    @OneToMany(mappedBy = "ciclo")
    private List<Asignatura> asignaturas;
}
