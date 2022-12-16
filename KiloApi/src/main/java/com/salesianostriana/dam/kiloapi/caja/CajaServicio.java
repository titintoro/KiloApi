package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaServicio {

    private final CajaRepositorio cajaRepo;
    private final TipoAlimentoRepository tipoAlimentoRepo;
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

    public Caja updateKgsOfTipoAlimentoFromCaja(Long idCaja, Long idTipoAlim, int cantidadKgs) {

        Optional<Caja> caja = findById(idCaja);

        if (caja.isPresent()){
            for (Tiene t:caja.get().getTieneList()){

                if(t.getTipoAlimento().getKilosDisp().getCantidadDisponible()>=cantidadKgs){

                    if (t.getTipoAlimento().getId().equals(idTipoAlim)  ) {
                        t.setCantidadKgs(cantidadKgs + t.getTipoAlimento().getKilosDisp().getCantidadDisponible());
                        t.getTipoAlimento().getKilosDisp().setCantidadDisponible(t.getTipoAlimento().getKilosDisp().getCantidadDisponible()-cantidadKgs);

                        cajaRepo.save(caja.get());
                        tipoAlimentoRepo.save(t.getTipoAlimento());
                        return caja.get();
                    }
                }
            }
        } return null;
    }

    public void deleteAlimFromCaja(Long id, Long idCaja){
        
        Optional<Caja> caja = findById(id);

        if (caja.isPresent()){
            for (Tiene t:caja.get().getTieneList()){

                if (t.getTipoAlimento().getId().equals(idCaja)) {

                    caja.get().getTieneList().remove(t);
                    cajaRepo.save(caja.get());
                }
            }
        }
    }
}
