package com.salesianostriana.dam.kiloapi.tipoAlimento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor @AllArgsConstructor
@Builder
public class TipoAlimentoResponse {

private String nombre;
private Long id;


}
