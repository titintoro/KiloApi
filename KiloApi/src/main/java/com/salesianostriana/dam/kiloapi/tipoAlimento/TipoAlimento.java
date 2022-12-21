package com.salesianostriana.dam.kiloapi.tipoAlimento;


import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class TipoAlimento {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "tipoAlimento", fetch = FetchType.EAGER, orphanRemoval = false)
    private List<Tiene> tieneList = new ArrayList<>();

    @OneToOne(mappedBy = "tipoAlimento", fetch = FetchType.EAGER, orphanRemoval = false, cascade = CascadeType.ALL)
    private KilosDisp kilosDisp;

    @OneToMany(mappedBy = "tipoAlimento" , fetch = FetchType.EAGER)
    @Builder.Default
    private List<DetalleAportacion> listaDetalleAportacion = new ArrayList<>();



    public void addKiloToTipo(KilosDisp kilos) {
        kilos.setId(this.getId());
        kilos.setTipoAlimento(this);
        this.kilosDisp = kilos;
    }

    public void removeKiloFromTipo(KilosDisp kilos) {
        kilos.setId(null);
        kilos.setTipoAlimento(null);
        this.kilosDisp = kilos;
    }
}


