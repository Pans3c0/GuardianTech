package org.educastur.samuelepv59.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AsuntoPropioDTO {
    private Integer id;
    private LocalDate diaSolicitado;
    private String descripcion;
    private LocalDateTime fechaTramitacion;
    private Boolean aprobado;
    private String nombreDocente; // Opcional
}
