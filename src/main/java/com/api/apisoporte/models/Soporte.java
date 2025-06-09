package com.api.apisoporte.models;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_soporte")
    private Integer idSoporte;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String descripcion;
    private String Tipo;
    private String Estado;
    private Date FechaCreacion;
    private Date FechaResolucion;
}
