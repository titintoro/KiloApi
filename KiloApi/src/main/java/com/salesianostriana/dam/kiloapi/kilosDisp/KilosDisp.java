package com.salesianostriana.dam.kiloapi.kilosDisp;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter  @Setter
public class KilosDisp {

    @ManyToOne(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoAlimento_id" , foreignKey = @ForeignKey(name = "FK_KILOSDISP_TIPOALIMENT"))
    @MapsId(tipoAlimento_id)
    private TipoAlimento id;

    private double cantidadDisponible;

}
