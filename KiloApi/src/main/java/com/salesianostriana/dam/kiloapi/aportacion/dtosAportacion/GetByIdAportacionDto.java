package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion.GetByIdDetallesAportacionDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetByIdAportacionDto {

    private List<GetByIdDetallesAportacionDto> details;

    public static GetByIdAportacionDto of (Aportacion aportacion){
        return new GetByIdAportacionDto(
               aportacion.getDetalleAportacionList().stream().map(GetByIdDetallesAportacionDto::of).toList()
        );

    }

}
