package com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion.GetByIdAportacionDto;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetByIdDetallesAportacionDto {

    private Long numLinea;
    private String nombreTipoAlimento;
    private double numKilos;

    public static GetByIdDetallesAportacionDto of (DetalleAportacion detalleAportacion){
        return new GetByIdDetallesAportacionDto(
                detalleAportacion.getNumLinea(),
                detalleAportacion.getTipoAlimento().getNombre(),
                detalleAportacion.getCantidadKilos()
        );

    }
}
