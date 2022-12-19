package com.salesianostriana.dam.kiloapi.tipoAlimento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TipoAlimentoRequest {

    private String nombre;

    private double kilosDisp;





}
