package com.salesianostriana.dam.kiloapi.caja.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

/*
* Todos los datos de la caja. Incluye una lista con
* los alimentos que contiene: id tipo alimento, nombre tipo, cantidad kilos; y los datos del destinatario: id, nombre
 * */
public class GetCajaResponse {

    private int numCaja;
    private String qr;
    private List<AlimentoGetCajaResponse> listaAlimentos;
    private DestinatarioGetCajaResponse destinatario;
    private double kilosTotales;

}
