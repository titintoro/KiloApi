package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAportacionDto{

    private Long id;
    private LocalDate fecha;
    private List<DetalleAportacion> detalleAportacionList;
    private Long idClase;

    public static CreateAportacionDto of (Aportacion a){
        return new CreateAportacionDto(
                a.getId(),
                a.getFecha(),
                a.getDetalleAportacionList(),
                a.getClase().getIdClase()
        );

    }

}
