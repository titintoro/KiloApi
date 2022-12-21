package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetDestinatarioInfoDto {

    private int numCaja;

    private double kgsTotales;

    private List<GetDestinatarioDetalleMoreInfoDto> listaDetalle;
}
