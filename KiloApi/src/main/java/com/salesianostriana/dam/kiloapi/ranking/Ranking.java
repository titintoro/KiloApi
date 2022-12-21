package com.salesianostriana.dam.kiloapi.ranking;

import lombok.*;


@Getter @Setter
@RequiredArgsConstructor @AllArgsConstructor
@Builder
public class Ranking {

    private String nombreClase;

    private double kgTotalesAportados;



    /*
    private int posicion;

    private int numAportacionesTotales;

    private double mediaKgPorAportacion;
     */

}
