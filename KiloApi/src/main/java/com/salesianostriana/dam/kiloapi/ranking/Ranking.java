package com.salesianostriana.dam.kiloapi.ranking;

import lombok.*;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Ranking {

    private String nombreClase;

    private double kgTotalesAportados;

    private int posicion;

    private Long numAportacionesTotales;

    private double mediaKgPorAportacion;

}
