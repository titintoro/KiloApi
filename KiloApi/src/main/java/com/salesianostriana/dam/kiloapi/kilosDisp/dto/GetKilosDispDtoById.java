package com.salesianostriana.dam.kiloapi.kilosDisp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetKilosDispDtoById {

    private Double kgDisponibles;

    private Long idTipoAlimento;

    private List<GetKilosDispDetalleAportacionDto> aportacion;


}
