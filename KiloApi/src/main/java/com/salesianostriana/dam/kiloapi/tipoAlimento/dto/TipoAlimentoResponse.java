package com.salesianostriana.dam.kiloapi.tipoAlimento.dto;

import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TipoAlimentoResponse {

    private String nombre;
    private Long id;

    public static TipoAlimentoResponse convertTipoAlimentoToResponse(TipoAlimento tipoAlimento) {
        TipoAlimentoResponse t = TipoAlimentoResponse.builder()
                .nombre(tipoAlimento.getNombre())
                .id(tipoAlimento.getId())
                .build();

        return t;
    }


}
