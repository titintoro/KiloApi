package com.salesianostriana.dam.kiloapi.kilosDisp.dto;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KilosDispConverter {

    public static GetKilosDispDto of (KilosDisp kd){
        return GetKilosDispDto.builder()
                .idTipoAlimento(kd.getTipoAlimento().getId())
                .nombre(kd.getTipoAlimento().getNombre())
                .kilosTotales(kd.getCantidadDisponible())
                .build();
    }

    public static GetKilosDispDtoById findByIdKilosDisp (KilosDisp kd){
        List<GetKilosDispDetalleAportacionDto> dtoList = new ArrayList<>();

        kd.getTipoAlimento().getListaDetalleAportacion().forEach(ld -> {
            dtoList.add(
                    GetKilosDispDetalleAportacionDto.builder()
                            .cantidadKgs(ld.getCantidadKilos())
                            .lineaDetalle(ld.getNumLinea())
                            .id(ld.getAportacion().getId())
                            .build()
            );
        });

        return GetKilosDispDtoById.builder()
                .idTipoAlimento(kd.getTipoAlimento().getId())
                .kgDisponibles(kd.getCantidadDisponible())
                .aportacion(dtoList)
                .build();
    }
}
