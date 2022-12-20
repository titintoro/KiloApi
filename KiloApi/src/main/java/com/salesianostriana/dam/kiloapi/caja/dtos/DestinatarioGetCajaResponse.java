package com.salesianostriana.dam.kiloapi.caja.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
/*
 * Todos los datos de la caja. Incluye una lista con
 * los alimentos que contiene: id tipo alimento, nombre tipo, cantidad kilos; y los datos del destinatario: id, nombre
 * */
public class DestinatarioGetCajaResponse {
    private Long id;
    private String nombre;
}
