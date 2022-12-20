package com.salesianostriana.dam.kiloapi.clase.dto;

import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ClaseResponse {

    private String clase;

    private int numAportacion;

    private double kilosAportados;

    public static ClaseResponse convertClaseToClaseResponse(Clase clase) {

        ClaseResponse resultado = ClaseResponse.builder()
                                       .clase(clase.getNombre())
                                       .numAportacion(clase.getListaAportaciones().size())
                                       .build();

    return resultado;

    }


}
