package com.salesianostriana.dam.kiloapi.clase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClaseRepository  extends JpaRepository<Clase,Long> {

   /* @Query("Select dt.cantidad from Clase c JOIN Aportacion a JOIN detalleAportacion dt Where c.id = :idClase")
    public double calcularKilosTotales(@Param("idClase") Long id);

*/

}
