package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateDestinatarioDto {

    private Long id;

    private String nombre, direccion, personaContacto, telefono;





}
