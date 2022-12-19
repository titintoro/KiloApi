package com.salesianostriana.dam.kiloapi.caja.dtos;

import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PostCajaAlimentoResponse {

    private int numCaja;
    private String qr;
    private List<AlimentoGetCajaResponse> listaAlimentos;
    private Destinatario destinatario;
    private double kilosTotales;
}
