package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetDestinatarioDto {

    private String nombre, direccion, personaContacto, telefono;
}
