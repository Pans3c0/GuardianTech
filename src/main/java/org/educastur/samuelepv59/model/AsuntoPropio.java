package org.educastur.samuelepv59.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "asuntos_propios")
public class AsuntoPropio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asunto")
    private Integer id;

    @Column(name = "dia_solicitado")
    private LocalDate diaSolicitado;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "fecha_tramitacion")
    private LocalDateTime fechaTramitacion;

    @Column(name = "aprobado")
    private Boolean aprobado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_docente")
    @JsonIgnore
    private Docente docente;
}