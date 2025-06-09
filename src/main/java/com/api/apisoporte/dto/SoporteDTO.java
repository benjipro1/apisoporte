package com.api.apisoporte.dto;

import java.sql.Date;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class SoporteDTO {

    private Integer idSoporte;
    private Integer idUsuario;
    private String descripcion;
    private String Tipo;
    private String Estado;
    private Date FechaCreacion;
    private Date FechaResolucion;
}
