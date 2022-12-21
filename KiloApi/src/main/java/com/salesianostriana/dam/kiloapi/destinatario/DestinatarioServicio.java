package com.salesianostriana.dam.kiloapi.destinatario;

import com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario.GetDestinatarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinatarioServicio {

    private final DestinatarioRepositorio repo;

    public Destinatario add(Destinatario destinatario) { return repo.save(destinatario);}



    public Optional<Destinatario> findById(Long id) {
        return repo.findById(id);
    }


    public List<Destinatario> findAll() {
        return repo.findAll();
    }


    public Destinatario edit(Destinatario destinatario) {
        return repo.save(destinatario);
    }


    public void delete(Destinatario destinatario) {
        repo.delete(destinatario);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public boolean existsById(Long id) { return repo.existsById(id);}
}
