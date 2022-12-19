package com.salesianostriana.dam.kiloapi.clase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClaseRepository  extends JpaRepository<Clase,Long> {


    @Query("""
            SELECT SUM(dt.cantidad)
            FROM Clase c JOIN Aportacion a ON a.clase = c
                    JOIN DetalleAportacion dt ON a.id = dt.aportacion.id
            WHERE c.id=?1
             """)
    double calcularKilosTotales(Long id);

}
