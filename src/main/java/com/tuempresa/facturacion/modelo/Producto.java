package com.tuempresa.facturacion.modelo;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Producto {
    @Id
    @Column(length = 10)
    int numero;

    @Column(length=50)
    @Required
    String descripcion;

    @Money
    BigDecimal precio;

    @Files
    @Column(length=50)
    String fotos;

    @TextArea
    String observaciones;

    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Categoria categoria;
}