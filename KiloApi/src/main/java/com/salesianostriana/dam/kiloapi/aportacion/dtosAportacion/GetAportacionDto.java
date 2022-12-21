package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetAportacionDto {

    private Long id;
    private LocalDate fecha;
    private String nombreClase;
    private double numKilos;


    public static GetAportacionDto of (Aportacion aportacion){
        return new GetAportacionDto(
                aportacion.getId(),
                aportacion.getFecha(),
                aportacion.getClase().getNombre(),
                aportacion.getKilos()
        );

    }



}
