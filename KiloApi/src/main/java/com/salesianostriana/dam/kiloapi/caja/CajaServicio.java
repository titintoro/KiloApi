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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaServicio {

    private final CajaRepositorio cajaRepo;
    private final KilosDispRepository kilosDispRepo;
    private final TipoAlimentoServicio tipoAlimentoServicio;
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


    public Optional<Caja> updateKgsOfTipoAlimentoFromCaja(Long idCaja, Long idTipoAlim, double cantidad) {

        Optional<Caja> caja = findById(idCaja);

        double kilosDisponibles = kilosDispRepo.getKilosDispOfAlimById(idTipoAlim);

        Optional<TipoAlimento> tipoAlimento = tipoAlimentoServicio.findById(idTipoAlim);


        if (caja.isPresent() && tipoAlimento.isPresent() && cantidad<=kilosDisponibles){

            Tiene tiene = new Tiene();

            for(Tiene t : caja.get().getTieneList()) {
                if (t.getTipoAlimento().getId().equals(idTipoAlim)) tiene = t;
            }

            tipoAlimento.get().getKilosDisp().setCantidadDisponible(kilosDisponibles + tiene.getCantidadKgs());

            caja.get().setKilosTotales(caja.get().getKilosTotales()+cantidad-tiene.getCantidadKgs());

            tiene.setCantidadKgs(cantidad);

            tipoAlimentoServicio.edit(tipoAlimento.get());

            tieneRepository.save(tiene);

            cajaRepo.save(caja.get());

            return caja;
        }
        return Optional.empty();


    }


    public void deleteAlimFromCaja(Long id, Long idTipoAlim){

        Optional<Caja> caja = findById(id);

        double kilosDisponibles = kilosDispRepo.getKilosDispOfAlimById(idTipoAlim);

        double kilosTotales = cajaRepo.getKilosTotales(id);

        Optional<Tiene> tiene1 = tieneRepository.findById(new TienePK(caja.get().getId(), tipoAlimentoServicio.findById(idTipoAlim).get().getId())) ;

        if (tiene1.isPresent()){
            Tiene tiene = new Tiene();

            for(Tiene t : caja.get().getTieneList()) {
                if (t.getTipoAlimento().getId().equals(idTipoAlim)) tiene = t;
            }

            double cantidadEliminada = tiene.getCantidadKgs();

            caja.get().setKilosTotales(kilosTotales-cantidadEliminada);

            tiene.getTipoAlimento().getKilosDisp().setCantidadDisponible(kilosDisponibles+cantidadEliminada);

            List<Tiene> tieneAuxList = new ArrayList<>();

            for(Tiene t : caja.get().getTieneList()){
                if (t.getTipoAlimento().getId()!=idTipoAlim){
                    tieneAuxList.add(t);

                }
            }
            tieneRepository.delete(tiene);
            caja.get().setTieneList(tieneAuxList);
            cajaRepo.save(caja.get());
        }




    }


    public Optional<Caja> addAlimToCaja(Long id, Long idTipoAlim, double cantidad){

        if (cajaRepo.findById(id).get().getDestinatario()!=null) {

            Optional<Caja> caja = cajaRepo.findById(id);
            Optional<TipoAlimento> tipoAlimento = tipoAlimentoServicio.findById(idTipoAlim);

            double kilosDisponibles = kilosDispRepo.getKilosDispOfAlimById(idTipoAlim);
            double kilosTotales = cajaRepo.getKilosTotales(id);

            if (caja.isPresent() && tipoAlimento.isPresent() && cantidad<=kilosDisponibles){

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

                    tieneRepository.save(tieneAniadido);

                    cajaRepo.save(caja.get());

                    return caja;

                } else {

                    for(Tiene t : caja.get().getTieneList()){

                        if (t.getTipoAlimento().getId().equals(idTipoAlim)){
                            tiene.get().setCantidadKgs(cantidad+t.getCantidadKgs());

                            caja.get().setKilosTotales(caja.get().getKilosTotales()+cantidad);

                            tipoAlimento.get().getKilosDisp().setCantidadDisponible(kilosDisponibles - cantidad);

                            tipoAlimentoServicio.edit(tipoAlimento.get());

                            tieneRepository.save(tiene.get());
                            cajaRepo.save(caja.get());

                            return caja;
                        }
                    }
                }
            }
        }

        return Optional.empty();

    }
}
