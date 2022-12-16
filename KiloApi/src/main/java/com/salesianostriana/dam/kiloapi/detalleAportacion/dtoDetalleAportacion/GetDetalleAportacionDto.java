package com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetDetalleAportacionDto {

    private LocalDate fecha;
    private String nombreTipoAlimento;
    private double numeroKilos;

    public static GetDetalleAportacionDto of (DetalleAportacion detalleAportacion) {
        return GetDetalleAportacionDto
                .builder()
                .fecha(detalleAportacion.getAportacion().getFecha())
                .nombreTipoAlimento(detalleAportacion.getTipoAlimento().getNombre())
                .numeroKilos(detalleAportacion.getCantidadKilos())
                .build();
    }
}
