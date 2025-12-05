package org.educastur.samuelepv59.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Falta {
    @Id
    private Integer id;
    private Date fecha;
    private String anotacion;
    private String material;

    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "docenteId")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "horario")
    private Horario horario;

}
