package org.educastur.samuelepv59.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "siglas", nullable = false, length = 10)
    private String siglas;

    @Column(name = "curso", nullable = false)
    private Integer curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciclo", nullable = false)
    private Ciclo ciclo;

    @OneToMany(mappedBy = "asignatura")
    private List<Horario> horarios;
}
