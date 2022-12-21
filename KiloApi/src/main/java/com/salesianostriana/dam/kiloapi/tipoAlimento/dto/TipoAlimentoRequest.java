package com.salesianostriana.dam.kiloapi.tipoAlimento.dto;

import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TipoAlimentoRequest {

    private String nombre;


    public static TipoAlimento convertTipoAlimentoRequestToTipoAlimento(TipoAlimentoRequest tipoAlimentoRequest){
        TipoAlimento t1 = TipoAlimento.builder()
                .nombre(tipoAlimentoRequest.getNombre())
                .kilosDisp(KilosDisp.builder()
                        .build())
                .build();

        return t1;
    }



}
