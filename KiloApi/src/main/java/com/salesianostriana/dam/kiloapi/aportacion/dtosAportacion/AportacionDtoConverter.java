package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.aportacion.AportacionServicio;
import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;
import org.springframework.stereotype.Component;

@Component
public class AportacionDtoConverter {

    private AportacionDtoConverter dtoConverter;

    private ClaseService claseService;

    public Aportacion createAportacionDtotoAportacion(CreateAportacionDto c) {
        return new Aportacion(
                c.getFecha(),
                c.getDetalleAportacionList()
        );
    }

    public GetAportacionDto aportacionToGetAportacionDto(Aportacion a) {

        return GetAportacionDto
                .builder()
                .fecha(a.getFecha())
                .nombreClase(a.getClase().getNombre())
                .numKilos(a.getKilos())
                .build();

    }


}







    /**public GetAportacionDto aportacionToGetAportacionDto(Aportacion a){

        if(a.getClase()== null) {
            a.setClase(null);
        }else {
            a.getClase();
        }
        return GetAportacionDto
                .builder()
                .fecha(a.getFecha())
                .nombreClase(a.getClase().getNombre())
                .numKilos(a.getKilos())
                .build();


    }}**/




