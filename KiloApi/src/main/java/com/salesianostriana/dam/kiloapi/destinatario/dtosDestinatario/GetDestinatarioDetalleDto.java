package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetDestinatarioDetalleDto {

    private Long id;

    private String nombre, direccion, personaContacto, telefono;

    private List<GetDestinatarioInfoDto> listaInfo;
}
