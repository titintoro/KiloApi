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
    private Long id;

    private Long numLinea;

    private double cantidad;

    @ManyToOne
    @JoinColumn(name = "aportacion_id", foreignKey = @ForeignKey(name = "FK_DETALLEAPORTACION_APORTACION"))
    private Aportacion aportacion;

    @ManyToOne
    @JoinColumn(name = "detalleAportacion", foreignKey = @ForeignKey(name = "FK_TIPOALIMENTO_DETALLEAPORTACION"))

    private TipoAlimento tipoAlimento;

    public DetalleAportacion addToAportacion(Aportacion a){
        this.aportacion = a;
        a.getDetalleAportacionList().add(this);
        return this;
    }
    public void removeFromAportacion(Aportacion a){
        this.aportacion= null;
        a.getDetalleAportacionList().remove(this);
    }

}
