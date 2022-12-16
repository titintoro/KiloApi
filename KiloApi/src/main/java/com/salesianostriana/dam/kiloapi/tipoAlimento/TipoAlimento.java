package com.salesianostriana.dam.kiloapi.tipoAlimento;


import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter@NoArgsConstructor @AllArgsConstructor
@Entity @Builder
public class TipoAlimento {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "tipoAlimento", fetch = FetchType.EAGER)
    private List<Tiene> tieneList = new ArrayList<>();

    @OneToOne(mappedBy = "tipoAlimento",fetch = FetchType.EAGER)
    private KilosDisp kilosDisp;
}
