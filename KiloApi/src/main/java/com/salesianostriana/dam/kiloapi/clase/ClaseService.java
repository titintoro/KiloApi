package com.salesianostriana.dam.kiloapi.clase;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.ranking.Ranking;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository repo;

    public Clase add(Clase clase) {
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

    public List<Ranking>getRanking(){return repo.getRankingOrdenado();}


    public Double calcularKg(Clase c){
       return repo.calcularKilosTotales(c.getIdClase());
    }

/*      List<Double> cantidad = c.getListaAportaciones().forEach(l -> {
            l.getDetalleAportacion().forEach(detalleAportacion -> {
                        detalleAportacion.getCantidad()
                    }
            );
        });

        for (Double kg : cantidad) {
            total +=kg ;
        }

        return total;
*/


}