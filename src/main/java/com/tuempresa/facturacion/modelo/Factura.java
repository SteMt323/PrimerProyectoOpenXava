package com.tuempresa.facturacion.modelo;
import java.time.*;
import java.util.Collection;
import javax.persistence.*;

import com.tuempresa.facturacion.calculadores.CalculadorSiguienteNumeroParaAnyo;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import lombok.*;

@Entity
@Getter @Setter
@View(members =
            "anio, numero, fecha;" +
            "cliente;"+
            "detalles;"+
            "observaciones"
    )
public class Factura {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length = 36)
    private Long id;

    @Column(length = 6)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    int anio;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class)
    LocalDate fecha;

    @TextArea
    String observaciones;

    @Column(length=6)
    @DefaultValueCalculator(value= CalculadorSiguienteNumeroParaAnyo.class,
            properties=@PropertyValue(name="anyo")
    )
    int numero;


    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @ReferenceView("Simple")
    Cliente cliente;

    @ElementCollection
    @ListProperties("producto.numero, producto.descripcion, cantidad")
    Collection<Detalle> detalles;
}
