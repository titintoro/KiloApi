package com.salesianostriana.dam.kiloapi.detalleAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DetalleAportacion {

    @Id
    @GeneratedValue
    private Long numLinea;

    @ManyToOne
    @JoinColumn(name = "Aportacion", foreignKey = @ForeignKey(name = "FK_DETALLEAPORTACION_APORTACION"))
    private Aportacion aportacion;

    private double cantidad;

    @ManyToOne
    @JoinColumn(name = "tipoAlimento_id", foreignKey = @ForeignKey(name = "FK_TIPOALIMENTO_DETALLEAPORTACION"))
    private TipoAlimento tipoAlimento;

}
