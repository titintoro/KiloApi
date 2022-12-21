package com.salesianostriana.dam.kiloapi.caja.dtos;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CajaDtoConverter {

    public Caja createCajaRequestToCaja(CreateCajaRequest createCajaRequest) {
        Caja cajaResponse = new Caja();

        cajaResponse.setQr(createCajaRequest.getQr());
        cajaResponse.setNumCaja(createCajaRequest.getNumCaja());
        cajaResponse.setKilosTotales(0);

        return cajaResponse;
    }

    public AlimentoGetCajaResponse toAlimentoGetCajaResponse(TipoAlimento tipoAlimento, Caja caja){

        AlimentoGetCajaResponse alimentoGetCajaResponse = new AlimentoGetCajaResponse();

        alimentoGetCajaResponse.setId(tipoAlimento.getId());
        alimentoGetCajaResponse.setNombre(tipoAlimento.getNombre());

        for(Tiene t : caja.getTieneList()){
            if( t.getTipoAlimento().getId().equals(tipoAlimento.getId()))
                alimentoGetCajaResponse.setCantidadKgs(t.getCantidadKgs());
        }

        return alimentoGetCajaResponse;
    }

    public GetCajaResponse toGetCajaResponse(Caja c, Destinatario d){

        GetCajaResponse getCajaResponse = new GetCajaResponse();
        List<AlimentoGetCajaResponse> alimentoGetCajaResponseList = new ArrayList<>();

        for (Tiene t : c.getTieneList()){
            alimentoGetCajaResponseList.add(toAlimentoGetCajaResponse(t.getTipoAlimento(),c));
        }

        getCajaResponse.setNumCaja(c.getNumCaja());
        getCajaResponse.setQr(c.getQr());
        getCajaResponse.setKilosTotales(c.getKilosTotales());
        getCajaResponse.setListaAlimentos(alimentoGetCajaResponseList);

        if(d==null)
            getCajaResponse.setDestinatario(null);
        if(d!=null)
            getCajaResponse.setDestinatario(DestinatarioGetCajaResponse.builder().id(d.getId()).nombre(d.getNombre()).build());

        return getCajaResponse;
    }

    public PostCajaAlimentoResponse toPostCajaAlimentoResponse(Caja c){

        PostCajaAlimentoResponse postCajaAlimentoResponse = new PostCajaAlimentoResponse();

        List<AlimentoGetCajaResponse> alimentoGetCajaResponseList = new ArrayList<>();

        for (Tiene t : c.getTieneList()){
            alimentoGetCajaResponseList.add(toAlimentoGetCajaResponse(t.getTipoAlimento(),c));
        }

        postCajaAlimentoResponse.setNumCaja(c.getNumCaja());
        postCajaAlimentoResponse.setQr(c.getQr());
        postCajaAlimentoResponse.setKilosTotales(c.getKilosTotales());
        postCajaAlimentoResponse.setDestinatario(destinatarioToDestinatarioGetCajaResponse(c.getDestinatario()));
        postCajaAlimentoResponse.setListaAlimentos(alimentoGetCajaResponseList);

        return postCajaAlimentoResponse;

    }

    public DestinatarioGetCajaResponse destinatarioToDestinatarioGetCajaResponse(Destinatario destinatario){
        DestinatarioGetCajaResponse destinatarioGetCajaResponse = new DestinatarioGetCajaResponse();
        destinatarioGetCajaResponse.setId(destinatario.getId());
        destinatarioGetCajaResponse.setNombre(destinatario.getNombre());

        return destinatarioGetCajaResponse;
    }
}
