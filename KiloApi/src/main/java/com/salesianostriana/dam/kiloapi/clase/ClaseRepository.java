package com.salesianostriana.dam.kiloapi.clase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClaseRepository  extends JpaRepository<Clase,Long> {

/*
    @Query("""
            SELECT new com.salesianostriana.dam.kiloapi.clase.dto.ClaseResponse(c1.nombre,c1.tutor,(SELECT SUM(dt.cantidad)
                                                                                                    FROM Clase c JOIN c.listaAportaciones a
                                                                                                         JOIN a.detalleAportacion dt
                                                                                                    WHERE c.id = c1))
            FROM Clase c1
            WHERE c1.id= :idClase
            
             """)
    double calcularKilosTotales(@Param("idClase") Long id);
*/

}
