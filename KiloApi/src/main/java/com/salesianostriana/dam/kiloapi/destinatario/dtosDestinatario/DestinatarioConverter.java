package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import org.springframework.stereotype.Component;

@Component
public class DestinatarioConverter {

    public Destinatario createDestinatarioToDestinatario(CreateDestinatario c){
        return new Destinatario(
                c.getDireccion(),
                c.getNombre(),
                c.getDireccion(),
                c.getPersonaContacto()
        );
    }

    public GetDestinatarioDto destinatarioToGetDestinatarioDto(Destinatario d, Caja c){
        return GetDestinatarioDto
                .builder()
                .direccion(d.getDireccion())
                .nombre(d.getNombre())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .kilosTotales(c.getKilosTotales())
                .numCajasAsiganadas(d.getListaCajas().size())
                .build();
    }
}
