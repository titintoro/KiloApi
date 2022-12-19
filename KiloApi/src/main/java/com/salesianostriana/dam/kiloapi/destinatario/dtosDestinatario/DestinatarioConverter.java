package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import org.springframework.stereotype.Component;

@Component
public class DestinatarioConverter {

    public Destinatario createDestinatarioToDestinatario(CreateDestinatarioDto c){
        return new Destinatario(
                c.getDireccion(),
                c.getNombre(),
                c.getTelefono(),
                c.getPersonaContacto()
        );
    }

    public GetDestinatarioDto destinatarioToGetDestinatarioDto(Destinatario d){
        return GetDestinatarioDto
                .builder()
                .id(d.getId())
                .direccion(d.getDireccion())
                .nombre(d.getNombre())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .cajasAsiganadas(d.listaIdDeCaja())
                .build();
    }
}
