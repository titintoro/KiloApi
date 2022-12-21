package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion.GetDetalleAportacionDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetNuevaAportacionDto {
    private Long id;
    private String nombreClase;
    private LocalDate fecha;
    private List<GetDetalleAportacionDto> listaDetalles;
}
