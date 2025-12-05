package org.educastur.samuelepv59.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "email")
    private String email;

    @Column(name = "siglas")
    private String siglas;

    @Column(name = "tipo")
    private String tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "antiguedad")
    private Date antiguedad;

    @Column(name = "guardias_realizadas", columnDefinition = "int default 0")
    private Integer guardiasRealizadas = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private List<AsuntoPropio> asuntosPropios;

    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private List<Horario> horarios;
}
