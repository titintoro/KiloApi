package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetDestinatarioDetalleMoreInfoDto {

    private Long idTipoAlimento;
    private String nombreTipoAlimento;
    private double KgsTipoAlimento;
}
