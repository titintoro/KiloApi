package com.salesianostriana.dam.kiloapi.kilosDisp.dto;

import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetKilosDispDto {

    private Long idTipoAlimento;

    private String nombre;

    private double kilosTotales;
}
