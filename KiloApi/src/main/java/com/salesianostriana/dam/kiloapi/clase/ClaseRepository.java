package com.salesianostriana.dam.kiloapi.clase;

import com.salesianostriana.dam.kiloapi.ranking.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

 /*   @Query("""
            SELECT SUM(dt.cantidad)
            FROM Clase c JOIN Aportacion a ON a.clase = c
                    JOIN DetalleAportacion dt ON a.id = dt.aportacion.id
            WHERE c.id=?1             """)
    double calcularKilosTotales(@Param("idClase") Long id);
*/
    @Query("""
            SELECT new com.salesianostriana.dam.kiloapi.ranking.Ranking (c1.nombre, ( SELECT SUM(dt.cantidad)
                                                                                     FROM Clase c JOIN Aportacion a ON a.clase = c
                                                                                                JOIN DetalleAportacion dt ON a.id = dt.aportacion.id
                                                                                     WHERE c1.id = c.id  ))
            FROM Clase c1 
            ORDER BY c1.nombre ASC
            """)
    List<Ranking> getRankingOrdenado();


}
