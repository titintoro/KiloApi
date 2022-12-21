package com.salesianostriana.dam.kiloapi.caja.dtos;

import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CajaResponse {


    private Long id;

    private String qr;

    private int numCaja;

    private double kilosTotales;

    private Destinatario destinatario;

    private List<Tiene> tieneList = new ArrayList<>();
}
