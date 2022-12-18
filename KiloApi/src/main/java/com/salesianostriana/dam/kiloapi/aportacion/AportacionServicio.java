package com.salesianostriana.dam.kiloapi.aportacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AportacionServicio {

    private final AportacionRepositorio repositorio;

    public Aportacion add(Aportacion aportacion){return repositorio.save(aportacion);}

    public Optional<Aportacion> findById(Long id) {
        return repositorio.findById(id);
    }

    public List<Aportacion> findAll() {
        return repositorio.findAll();
    }

    public Aportacion edit(Aportacion aportacion) {
        return repositorio.save(aportacion);
    }

    public void delete(Aportacion aportacion) {
        repositorio.delete(aportacion);
    }

    public void deleteById(Long id) {
        repositorio.deleteById(id);
    }

    public boolean aportacionCorrecta(Aportacion a){
        boolean res = false;
        if(a.getKilos()*100> 1 && a.getFecha().getYear() > 2021 && !a.getDetalleAportacionList().isEmpty()){
            res = true;
        }
        return res;

    }
}
