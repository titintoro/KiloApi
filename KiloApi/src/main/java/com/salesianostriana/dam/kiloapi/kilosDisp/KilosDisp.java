package com.salesianostriana.dam.kiloapi.kilosDisp;

import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter  @Setter
public class KilosDisp {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoAlimento_id" , foreignKey = @ForeignKey(name = "FK_KILOSDISP_TIPOALIMENT"))
    @MapsId
    private TipoAlimento tipoAlimento;

    @Id
    private Long id;

    private double cantidadDisponible;

}
