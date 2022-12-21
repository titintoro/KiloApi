package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetDestinatarioDto {

    private Long id;

    private String nombre, direccion, personaContacto, telefono;

    private double kilosTotales;

    private List<GetDestinatarioCajasIdDto> cajasAsiganadas;
}
