package com.salesianostriana.dam.kiloapi.kilosDisp.dto;

import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import org.springframework.stereotype.Component;

@Component
public class KilosDispConverter {

    public GetKilosDispDto kilosDispToGetKilosDispDto(KilosDisp kd){
        return GetKilosDispDto.builder()
                .idTipoAlimento(kd.getTipoAlimento().getId())
                .nombre(kd.getTipoAlimento().getNombre())
                .kilosTotales(kd.getCantidadDisponible())
                .build();
    }

    public GetKilosDispDtoById kilosDispToGetKilosDispDtoById(KilosDisp kd){
        return GetKilosDispDtoById.builder()
                .idTipoAlimento(kd.getTipoAlimento().getId())
                .kgDisponibles(kd.getCantidadDisponible())
                .idAportacion(kd.getTipoAlimento().listaIdDeDetalleAportacion())
                .kgAportacion(kd.getTipoAlimento().listaCantidadKgsDetalleAportacion())
                .lineaDetalle(kd.getTipoAlimento().listaNumLineaDetalleAportacion())
                .build();
    }
}
