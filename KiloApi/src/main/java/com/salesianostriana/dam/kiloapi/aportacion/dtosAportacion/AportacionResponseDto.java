package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion.DetalleAportacionResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AportacionResponseDto {

    private LocalDate fecha;
    private List<DetalleAportacionResponseDto> detalles;
    private Long idClase;

}
