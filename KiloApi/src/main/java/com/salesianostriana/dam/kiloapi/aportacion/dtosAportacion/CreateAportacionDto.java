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

    private List<DetalleAportacion> detalleAportacionList;
    private Long idClase;

    //fecha de ahora, y en el tema de la lista de aportaciones: tipo alimento, kgs
    //tendria que crear detalles de aportacion por cada elemento de la lista de detalles
    //hacer los metodos en el servicio
    //para recorrer un mapa, es un for each con dos variables


}
