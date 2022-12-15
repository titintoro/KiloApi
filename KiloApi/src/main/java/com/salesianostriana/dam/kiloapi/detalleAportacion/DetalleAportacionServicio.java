package com.salesianostriana.dam.kiloapi.detalleAportacion;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.aportacion.AportacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleAportacionServicio {

    private final DetalleAportacionRepositorio repositorio;

    public DetalleAportacion add(DetalleAportacion detalleAportacion){return repositorio.save(detalleAportacion);}

    public Optional<DetalleAportacion> findById(Long id) {
        return repositorio.findById(id);
    }

    public List<DetalleAportacion> findAll() {
        return repositorio.findAll();
    }

    public DetalleAportacion edit(DetalleAportacion detalleAportacion) {
        return repositorio.save(detalleAportacion);
    }

    public void delete(DetalleAportacion detalleAportacion) {
        repositorio.delete(detalleAportacion);
    }

    public void deleteById(Long id) {
        repositorio.deleteById(id);
    }
}

