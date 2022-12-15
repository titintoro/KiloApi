package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaServicio {

    private final CajaRepositorio repo;

    public Caja add(Caja caja) { return repo.save(caja);}


    public Optional<Caja> findById(Long id) {
        return repo.findById(id);
    }


    public List<Caja> findAll() {
        return repo.findAll();
    }


    public Caja edit(Caja caja) {
        return repo.save(caja);
    }


    public void delete(Caja caja) {
        repo.delete(caja);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<Tiene> findListaTiene() { return repo.findListOFTiene();}
    public boolean existsById(Long id) { return repo.existsById(id);}
}
