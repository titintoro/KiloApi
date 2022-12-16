package com.salesianostriana.dam.kiloapi.kilosDisp;

import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter  @Setter
public class KilosDisp {

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = false)
    private TipoAlimento tipoAlimento;

    @Id
    private Long id;

    private double cantidadDisponible;

}
