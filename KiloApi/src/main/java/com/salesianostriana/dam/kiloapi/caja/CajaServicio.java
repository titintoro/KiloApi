package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.caja.dtos.CajaDtoConverter;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispRepository;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import com.salesianostriana.dam.kiloapi.tiene.TienePK;
import com.salesianostriana.dam.kiloapi.tiene.TieneRepository;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimentoRepository;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimentoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaServicio {

    private final CajaRepositorio cajaRepo;
    private final KilosDispRepository kilosDispRepo;
    private final TipoAlimentoServicio tipoAlimentoServicio;
    private final CajaDtoConverter cajaDtoConverter;
    private final KilosDispService kilosDispService;
    private final TipoAlimentoRepository tipoAlimentoRepository;
    private final TieneRepository tieneRepository;

    public Caja add(Caja caja) { return cajaRepo.save(caja);}


    public Optional<Caja> findById(Long id) {
        return cajaRepo.findById(id);
    }


    public List<Caja> findAll() {
        return cajaRepo.findAll();
    }


    public Caja edit(Caja caja) {
        return cajaRepo.save(caja);
    }


    public void delete(Caja caja) {
        cajaRepo.delete(caja);
    }


    public void deleteById(Long id) {
        cajaRepo.deleteById(id);
    }


    public boolean existsById(Long id) { return cajaRepo.existsById(id);}


    public Optional<Caja> updateKgsOfTipoAlimentoFromCaja(Long idCaja, Long idTipoAlim, double cantidadKgs) {

        Optional<Caja> caja = findById(idCaja);
        double kilosDisponibles = kilosDispRepo.getKilosDispOfAlimById(idTipoAlim);

        if (caja.isPresent()){
            for (Tiene t:caja.get().getTieneList()){

                if(kilosDisponibles>=cantidadKgs){

                    if (t.getTipoAlimento().getId().equals(idTipoAlim)  ) {
                        t.setCantidadKgs(cantidadKgs + t.getCantidadKgs());
                        t.getTipoAlimento().getKilosDisp().setCantidadDisponible(kilosDisponibles-cantidadKgs);


                        kilosDispRepo.save(t.getTipoAlimento().getKilosDisp());
                        cajaRepo.save(caja.get());
                        tipoAlimentoServicio.add(t.getTipoAlimento());
                        return caja;
                    }
                }
            }
        }
        return caja;
    }


    public void deleteAlimFromCaja(Long id, Long idAlim){

        Optional<Caja> caja = findById(id);
        double kilosDisponibles = kilosDispRepo.getKilosDispOfAlimById(idAlim);
        double kilosTotales = cajaRepo.getKilosTotales(id);

        if (caja.isPresent()){
            for (Tiene t:caja.get().getTieneList()){

                if (t.getTipoAlimento().getId().equals(idAlim)) {

                    double cantidadEliminada = t.getCantidadKgs();
                    caja.get().setKilosTotales(kilosTotales-cantidadEliminada);
                    t.getTipoAlimento().getKilosDisp().setCantidadDisponible(kilosDisponibles+cantidadEliminada);
                    caja.get().getTieneList().remove(t);
                    cajaRepo.save(caja.get());
                }
            }
        }
    }


    public Optional<Caja> addAlimToCaja(Long id, Long idTipoAlim, double cantidad){

        if (cajaRepo.findById(id).get().getDestinatario()!=null) {

            Optional<Caja> caja = cajaRepo.findById(id);
            Optional<TipoAlimento> tipoAlimento = tipoAlimentoServicio.findById(idTipoAlim);


            double kilosDisponibles = kilosDispRepo.getKilosDispOfAlimById(idTipoAlim);
            double kilosTotales = cajaRepo.getKilosTotales(id);

            if (caja.isPresent() && tipoAlimento.isPresent() && cantidad>=kilosDisponibles){

                Optional<Tiene> tiene = tieneRepository.findById(new TienePK(caja.get().getId(), tipoAlimento.get().getId())) ;

                if (tiene.isEmpty()){

                    Tiene tieneAniadido = new Tiene();
                    tieneAniadido.setCaja(caja.get());
                    tieneAniadido.setId(new TienePK(caja.get().getId(), tipoAlimento.get().getId()));
                    tieneAniadido.setTipoAlimento(tipoAlimentoServicio.findById(idTipoAlim).get());
                    tieneAniadido.setCantidadKgs(cantidad);


                    caja.get().setKilosTotales(kilosTotales+cantidad);

                    tipoAlimento.get().getKilosDisp().setCantidadDisponible(kilosDisponibles - cantidad);

                    caja.get().getTieneList().add(tieneAniadido);

                    tipoAlimentoServicio.edit(tipoAlimento.get());

                    caja.get().setKilosTotales(caja.get().getKilosTotales()+cantidad);

                    tieneRepository.save(tieneAniadido);

                    cajaRepo.save(caja.get());

                    return caja;

                } else {
                    caja.get().setKilosTotales(kilosTotales+cantidad);

                    tiene.get().setCantidadKgs(cantidad);

                    tipoAlimento.get().getKilosDisp().setCantidadDisponible(kilosDisponibles - cantidad);

                    caja.get().getTieneList().add(tiene.get());

                    tipoAlimentoServicio.edit(tipoAlimento.get());
                    caja.get().setKilosTotales(caja.get().getKilosTotales()+cantidad);

                    tieneRepository.save(tiene.get());
                    cajaRepo.save(caja.get());

                    return caja;
                }



            }

        }

        return Optional.empty();



    }
}
