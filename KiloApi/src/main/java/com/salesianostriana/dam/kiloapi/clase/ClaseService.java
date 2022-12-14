package com.salesianostriana.dam.kiloapi.clase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository repo;

    public Clase add(Clase clase){
        return repo.save(clase);
    }

    public Optional<Clase> findById(Long id) {
        return repo.findById(id);
    }

    public List<Clase> findAll() {
        return repo.findAll();
    }

    public Clase edit(Clase clase) {
        return repo.save(clase);
    }

    public void delete(Clase clase) {
        repo.delete(clase);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
