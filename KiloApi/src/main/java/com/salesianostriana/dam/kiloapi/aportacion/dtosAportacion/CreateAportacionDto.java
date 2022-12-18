package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateAportacionDto{

    private Long id;
    private LocalDate fecha;
    private List<DetalleAportacion> detalleAportacionList;


}
