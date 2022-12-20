package com.salesianostriana.dam.kiloapi.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CajaRepositorio extends JpaRepository<Caja, Long> {

    @Query("""
            SELECT c
                FROM Caja c JOIN Destinatario d ON c.destinatario.id = d.id
                WHERE d.id = ?1
            """)
    List<Caja> getCajasByIdDestinatario(Long id);

    @Query("""
            SELECT c.kilosTotales
                FROM Caja c
                WHERE c.id = ?1
            """)
    Double getKilosTotales(Long id);
}
