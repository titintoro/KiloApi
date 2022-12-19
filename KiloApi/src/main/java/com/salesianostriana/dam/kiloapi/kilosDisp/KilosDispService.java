package com.salesianostriana.dam.kiloapi.kilosDisp;

import com.salesianostriana.dam.kiloapi.clase.Clase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KilosDispService {

    private final KilosDispRepository repo;

    public KilosDisp add(KilosDisp kilosDisp){
        return repo.save(kilosDisp);
    }

    public Optional<KilosDisp> findById(Long id) {
        return repo.findById(id);
    }

    public List<KilosDisp> findAll() {
        return repo.findAll();
    }

    public KilosDisp edit(KilosDisp kilosDisp) {
        return repo.save(kilosDisp);
    }

    public void delete(KilosDisp kilosDisp) {
        repo.delete(kilosDisp);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


}
