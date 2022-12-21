package com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DetalleAportacionResponseDto {
    private Long idTipoAlimento;
    private double kilosAlimento;


}
