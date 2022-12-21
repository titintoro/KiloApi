package com.salesianostriana.dam.kiloapi.clase;

import com.salesianostriana.dam.kiloapi.ranking.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
/*
 @Query(    """ 
                SELECT SUM(dt.cantidadKilos)
                FROM Clase c 
                JOIN Aportacion a ON c.id = a.clase
                JOIN DetalleAportacion dt ON a.id = dt.aportacion
                JOIN tipoAlimento ta ON dt.tipoAlimento = ta.id
                WHERE c.id= :idClase     
        
           """)
    Double calcularKilosTotales(@Param("idClase") Long id);

    @Query("""
            SELECT new com.salesianostriana.dam.kiloapi.ranking.Ranking (c1.nombre, (   SELECT SUM(dt.cantidadKilos)
                                                                                        FROM Clase c 
                                                                                        JOIN Aportacion a ON c.id = a.clase
                                                                                        JOIN DetalleAportacion dt ON a.id = dt.aportacion
                                                                                        JOIN tipoAlimento ta ON dt.tipoAlimento = ta.id
                                                                                        WHERE c.id = c1.id  ))
            FROM Clase c1 
            ORDER BY c1.nombre ASC
            """)
    List<Ranking> getRankingOrdenado();
*/
}
