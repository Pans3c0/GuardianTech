package org.educastur.samuelepv59.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocenteCreateDTO {

    private Integer numemp;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private Integer idDepartamento;
}
