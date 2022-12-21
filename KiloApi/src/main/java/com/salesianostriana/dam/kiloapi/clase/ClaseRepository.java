package com.salesianostriana.dam.kiloapi.clase;

import com.salesianostriana.dam.kiloapi.ranking.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

   @Query("""
            SELECT SUM(dt.cantidad)
            FROM Clase c 
            JOIN Aportacion a ON a.clase = c
            JOIN DetalleAportacion dt ON a.id = dt.aportacion.id
            
            WHERE c.id= :idClase
            GROUP BY a
            """)
    Double calcularKilosTotales(@Param("idClase") Long id);


    @Query("""
            SELECT new com.salesianostriana.dam.kiloapi.ranking.Ranking (c1.nombre, count(a),SUM(dt.cantidad,ROUND(AVG(dt.cantidad),2))
            FROM Clase c1 
            LEFT JOIN c1.listaAportaciones a 
            LEFT JOIN a.detalleAportacionList dt
            GROUP BY c1.nombre
            ORDER BY SUM(dt.cantidad) DESC
            """)
    List<Ranking> getRankingOrdenado();

}
