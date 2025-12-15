package org.educastur.samuelepv59.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "docentes")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Integer id;

    private String nombre;

    private String apellidos;

    private String email;

    private String siglas;

    private String tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "antiguedad")
    private Date antiguedad;

    @Column(name = "guardias_realizadas", columnDefinition = "int default 0")
    private Integer guardiasRealizadas = 0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    @JsonIgnore
    private Departamento departamento;

    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private List<AsuntoPropio> asuntosPropios;
    @JsonIgnore
    @OneToMany(mappedBy = "docente")

    private List<Horario> horarios;
}
