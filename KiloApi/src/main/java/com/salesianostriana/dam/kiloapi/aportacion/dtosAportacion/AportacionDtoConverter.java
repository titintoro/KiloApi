package com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.aportacion.AportacionServicio;
import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;
import com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion.GetDetalleAportacionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AportacionDtoConverter {

    private AportacionDtoConverter dtoConverter;

    private ClaseService claseService;

    /**
    public Aportacion createAportacionDtotoAportacion(CreateAportacionDto c) {
        return Aportacion
                .builder()
                .id(c.getIdClase())
                .detalleAportacionList(c.getDetalleAportacionList())
                .build();
    }**/

    public GetAportacionDto aportacionToGetAportacionDto(Aportacion a) {

        return GetAportacionDto
                .builder()
                .fecha(a.getFecha())
                .nombreClase(a.getClase().getNombre())
                .numKilos(a.getKilos())
                .build();

    }

    public GetNuevaAportacionDto nuevaAportacionDto(Aportacion a){
        List<GetDetalleAportacionDto> dList = new ArrayList<>();

        a.getDetalleAportacionList().forEach(d -> {
            dList.add(
                    GetDetalleAportacionDto.builder()
                            .numLinea(d.getNumLinea())
                            .nombreTipoAlimento(d.getTipoAlimento().getNombre())
                            .numeroKilos(d.getCantidadKilos())
                            .build()
            );
        });

        return GetNuevaAportacionDto.builder()
                .id(a.getId())
                .nombreClase(a.getClase().getNombre())
                .fecha(a.getFecha())
                .listaDetalles(dList)
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




