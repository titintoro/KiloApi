package com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DestinatarioConverter {

    public Destinatario of(CreateDestinatarioDto c){
        return new Destinatario(
                c.getDireccion(),
                c.getNombre(),
                c.getTelefono(),
                c.getPersonaContacto()
        );
    }

    public static GetDestinatarioDto of(Destinatario d){
        List<GetDestinatarioCajasIdDto> listaCajasId = new ArrayList<>();

        d.getListaDeCajas().forEach(ld -> {
            listaCajasId.add(
                    GetDestinatarioCajasIdDto.builder()
                            .numCaja(ld.getNumCaja())
                            .build()
            );
        });

        return GetDestinatarioDto
                .builder()
                .id(d.getId())
                .direccion(d.getDireccion())
                .nombre(d.getNombre())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .cajasAsiganadas(listaCajasId)
                .build();
    }

    public GetDestinatarioDtoById destinatarioToGetDestinatarioDtoById(Destinatario d){
        List<GetDestinatarioDtoByIdCajas> listaCajasId = new ArrayList<>();

        d.getListaDeCajas().forEach(ld -> {
            listaCajasId.add(
                    GetDestinatarioDtoByIdCajas.builder()
                            .numCaja(ld.getNumCaja())
                            .kgsAsignados(ld.getKilosTotales())
                            .build()
            );
        });

        return GetDestinatarioDtoById.builder()
                .id(d.getId())
                .direccion(d.getDireccion())
                .nombre(d.getNombre())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .cajasAsiganadas(listaCajasId)
                .build();
    }

    public GetDestinatarioDetalleDto destinatarioDetalleToDestinatarioDetalleDto (Destinatario d){
        List<GetDestinatarioInfoDto> listaInfoDestinatario = new ArrayList<>();
        List<GetDestinatarioDetalleMoreInfoDto> listaMoreInfoDestinatario = new ArrayList<>();



        d.getListaDeCajas().forEach(ld -> {
            listaInfoDestinatario.add(
                    GetDestinatarioInfoDto.builder()
                            .numCaja(ld.getNumCaja())
                            .kgsTotales(ld.getKilosTotales())
                            .listaDetalle(listaMoreInfoDestinatario)
                            .build()
            );
        });
    }
}
