package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import org.springframework.stereotype.Component;

@Component
public class AportacionDtoConverter {

    private AportacionDtoConverter dtoConverter;

    public Aportacion createAportacionDtotoAportacion(CreateAportacionDto c){
        return new Aportacion(
                c.getFecha(),
                c.getDetalleAportacionList()
        );
    }


}
