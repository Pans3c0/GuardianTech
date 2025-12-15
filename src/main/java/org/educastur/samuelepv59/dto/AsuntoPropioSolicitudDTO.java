package org.educastur.samuelepv59.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AsuntoPropioSolicitudDTO {
    private Integer idDocente;
    private LocalDate diaSolicitado;
    private String descripcion;
}
