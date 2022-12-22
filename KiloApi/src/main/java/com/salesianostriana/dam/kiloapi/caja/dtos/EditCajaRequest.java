package com.salesianostriana.dam.kiloapi.caja.dtos;


import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EditCajaRequest {

    private int numCaja;
    private String qr;
    private Long idDestinatario;
}
