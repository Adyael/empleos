package com.iaaj.model;

import java.util.Date;
import lombok.Data;


@Data
public class Vacante {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private String estatus = "Aprobada";
    private Double salario;
    private Integer destacado;
    private String imagen = "no-image.png";
    private String detalles;
    private Categoria categoria;
}
