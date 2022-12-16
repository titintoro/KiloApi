package com.salesianostriana.dam.kiloapi.caja.dtos;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CajaDtoConverter {

    public Caja createCajaRequestToCaja(CreateCajaRequest createCajaRequest, List<Tiene> tieneList) {
        Caja cajaResponse = new Caja();

        cajaResponse.setQr(createCajaRequest.getQr());
        cajaResponse.setNumCaja(createCajaRequest.getNumCaja());
        cajaResponse.setKilosTotales(0);

        return cajaResponse;
    }
}
