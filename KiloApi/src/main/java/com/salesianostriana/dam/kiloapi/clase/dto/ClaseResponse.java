package com.salesianostriana.dam.kiloapi.clase.dto;

import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;
import lombok.*;

@Data
@RequiredArgsConstructor @AllArgsConstructor
@Builder
public class ClaseResponse {

private Clase clase;

private int numAportacion;

private double kilosAportados;





}
