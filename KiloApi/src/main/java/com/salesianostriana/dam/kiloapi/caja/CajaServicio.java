package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.caja.dtos.CajaDtoConverter;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
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
    private final TipoAlimentoServicio tipoAlimentoServicio;
    private final CajaDtoConverter cajaDtoConverter;
    private final KilosDispService kilosDispService;

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

        if (caja.isPresent()){
            for (Tiene t:caja.get().getTieneList()){

                if(t.getTipoAlimento().getKilosDisp().getCantidadDisponible()>=cantidadKgs){

                    if (t.getTipoAlimento().getId().equals(idTipoAlim)  ) {
                        t.setCantidadKgs(cantidadKgs + t.getTipoAlimento().getKilosDisp().getCantidadDisponible());
                        t.getTipoAlimento().getKilosDisp().setCantidadDisponible(t.getTipoAlimento().getKilosDisp().getCantidadDisponible()-cantidadKgs);

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

        if (caja.isPresent()){
            for (Tiene t:caja.get().getTieneList()){

                if (t.getTipoAlimento().getId().equals(idAlim)) {

                    caja.get().getTieneList().remove(t);
                    cajaRepo.save(caja.get());
                }
            }
        }
    }


    public Optional<Caja> addAlimToCaja(Long id, Long idTipoAlim, double cantidad){

        Optional<Caja> caja = cajaRepo.findById(id);
        Optional<TipoAlimento> tipoAlimento = tipoAlimentoServicio.findById(idTipoAlim);

        if (caja.isPresent() && tipoAlimento.isPresent()){

            Tiene tiene = new Tiene();
            tiene.setTipoAlimento(tipoAlimento.get());
            tiene.setCaja(caja.get());
            tiene.setCantidadKgs(cantidad);

            tipoAlimento.get().getKilosDisp().setCantidadDisponible( tipoAlimento.get().getKilosDisp().getCantidadDisponible()-cantidad);

            caja.get().getTieneList().add(tiene);

            tipoAlimentoServicio.edit(tipoAlimento.get());
            cajaRepo.save(caja.get());

             return caja;

        }

        return caja;



    }
}
