package com.salesianostriana.dam.kiloapi.kilosDisp;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter  @Setter
public class KilosDisp {

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @MapsId
    private TipoAlimento tipoAlimento;

    @Id
    private Long id;

    private double cantidadDisponible;

    public void addTipoToKilo(TipoAlimento tp){
        this.tipoAlimento = tp;
        tp.setId(this.getId());
        tp.setKilosDisp(this);
    }

}
