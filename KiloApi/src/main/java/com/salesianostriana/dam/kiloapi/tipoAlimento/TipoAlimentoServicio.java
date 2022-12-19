package com.salesianostriana.dam.kiloapi.tipoAlimento;

import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispRepository;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoAlimentoServicio {

    private final TipoAlimentoRepository repository;

    private final KilosDispRepository kilosDispRepository;


    public TipoAlimento add(TipoAlimento tipoAlimento) {
        return repository.save(tipoAlimento);
    }

    public Optional<TipoAlimento> findById(Long id) {
        return repository.findById(id);
    }


    public List<TipoAlimento> findAll() {
        return repository.findAll();
    }


    public TipoAlimento edit(TipoAlimento tipoAlimento) {
        return repository.save(tipoAlimento);
    }


    public void delete(TipoAlimento tipoAlimento) {
        repository.delete(tipoAlimento);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public TipoAlimento checkCantidad(Long id) {
        Optional<TipoAlimento> aux = findById(id);

        if (aux.isPresent() && kilosDispRepository.getKilosDispOfAlimById(id) > 0) {
            aux.get().getKilosDisp().setCantidadDisponible(0);
            return aux.get();
        }
        return aux.get();
    }

}