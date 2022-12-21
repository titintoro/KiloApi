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

    private Long numLinea;
    private String nombreTipoAlimento;
    private double numeroKilos;

    public static GetDetalleAportacionDto of (DetalleAportacion detalleAportacion) {
        return GetDetalleAportacionDto
                .builder()
                .numLinea(detalleAportacion.getNumLinea())
                .nombreTipoAlimento(detalleAportacion.getTipoAlimento().getNombre())
                .numeroKilos(detalleAportacion.getCantidad())
                .build();
    }
}
