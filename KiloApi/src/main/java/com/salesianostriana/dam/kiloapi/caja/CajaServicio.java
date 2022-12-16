package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaServicio {

    private final CajaRepositorio cajaRepo;
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

    public List<Tiene> findListaTiene() { return cajaRepo.findListOFTiene();}
    public boolean existsById(Long id) { return cajaRepo.existsById(id);}

    public Caja updateKgsOfTipoAlimentoFromCaja(Long idCaja, Long idTipoAlim, int cantidadKgs) {

        Optional<Caja> caja = findById(idCaja);

        if (caja.isPresent()){
            for (Tiene t:caja.get().getTieneList()){

                if(t.getTipoAlimento().getKilosDisponibles().getCantidad()>=cantidadKgs){

                    if (t.getTipoAlimento().getId().equals(idTipoAlim)  ) {
                        t.setCantidadKgs(cantidadKgs + t.getTipoAlimento().getKilosDisponibles().getCantidad());
                        t.getTipoAlimento().getKilosDisponibles().setCantidad(t.getTipoAlimento().getKilosDisponibles()-cantidadKgs);

                        cajaRepo.save(caja.get());

                        return caja.get();
                    }
                }
            }
        } return null;
    }
}
