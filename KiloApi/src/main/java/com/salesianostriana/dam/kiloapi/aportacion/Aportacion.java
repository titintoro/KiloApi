package com.salesianostriana.dam.kiloapi.aportacion;

import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Aportacion {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;


    @OneToMany(orphanRemoval = true, mappedBy = "aportacion" , fetch = FetchType.EAGER)
    @Builder.Default
    private List<DetalleAportacion> detalleAportacionList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "listaAportacion", foreignKey = @ForeignKey(name = "FK_APORTACION_CLASE"))
    private Clase clase;

    private void addToClase(Clase c){
        this.clase = c;
        c.getListaAportaciones().add(this);
    }

    private void removeFromClase(Clase c){
        this.clase = null;
        c.getListaAportaciones().remove(this);
    }

    public double getKilos(){
        double CantidadKilos = 0;
        for (DetalleAportacion detalleAportacion:detalleAportacionList) {
            CantidadKilos = CantidadKilos + detalleAportacion.getCantidadKilos();
        }
        return CantidadKilos;

    }


}
