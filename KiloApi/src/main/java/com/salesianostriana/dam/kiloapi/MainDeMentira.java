package com.salesianostriana.dam.kiloapi;

import com.salesianostriana.dam.kiloapi.aportacion.AportacionServicio;
import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
import com.salesianostriana.dam.kiloapi.clase.ClaseRepository;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;
import com.salesianostriana.dam.kiloapi.destinatario.DestinatarioServicio;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacionServicio;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimentoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MainDeMentira{

    private final ClaseService claseService;

    private final CajaServicio cajaServicio;

    private final AportacionServicio aportacionServicio;

    private final DestinatarioServicio destinatarioServicio;

    private final DetalleAportacionServicio detalleAportacionServicio;

    private final KilosDispService kilosDispService;

    private final TipoAlimentoServicio tipoAlimentoServicio;

    @PostConstruct
    public void run(){

        

    }

}
