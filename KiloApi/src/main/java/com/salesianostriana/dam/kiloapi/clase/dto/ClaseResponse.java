package com.salesianostriana.dam.kiloapi.clase.dto;

import com.salesianostriana.dam.kiloapi.clase.Clase;
import lombok.*;

@Data
@RequiredArgsConstructor @AllArgsConstructor @NoArgsConstructor
@Builder

public class ClaseResponse {

private Clase clase;

private int numAportacion;

private double kilosAportados;


public static ClaseResponse convertClaseToClaseResponse(Clase c){

            ClaseResponse result = ClaseResponse.builder()
                                        .clase(c)
                                        .numAportacion(c.getListaAportaciones().size())
                                      //.kilosAportados()
                                        .build();

            return result;
}



}
