package com.api.apisoporte.dto;

import java.sql.Date;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class SoporteDTO extends RepresentationModel<SoporteDTO>{

    private Integer idSoporte;
    private Integer idUsuario;
    private String descripcion;
    private String Tipo;
    private String Estado;
    private Date FechaCreacion;
    private Date FechaResolucion;
}
