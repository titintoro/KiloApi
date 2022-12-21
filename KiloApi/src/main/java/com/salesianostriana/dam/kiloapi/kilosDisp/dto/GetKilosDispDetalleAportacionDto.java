package com.salesianostriana.dam.kiloapi.kilosDisp.dto;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetKilosDispDetalleAportacionDto {

    private Long id;

    private Long lineaDetalle;

    private double cantidadKgs;

    public GetKilosDispDetalleAportacionDto of (DetalleAportacion d){
        return GetKilosDispDetalleAportacionDto.builder()
                .id(d.getAportacion().getId())
                .lineaDetalle(d.getNumLinea())
                .cantidadKgs(d.getCantidadKilos())
                .build();
    }
}
