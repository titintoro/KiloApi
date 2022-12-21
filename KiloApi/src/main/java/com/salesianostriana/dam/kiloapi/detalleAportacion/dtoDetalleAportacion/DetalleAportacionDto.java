package com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

// id tipo alimento, y los kilos de ese tipo alimento
public class DetalleAportacionDto {

    private Long idTipoAlimento;
    private double numKilos;
}
