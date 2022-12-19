package com.salesianostriana.dam.kiloapi.kilosDisp;


import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KilosDispRepository extends JpaRepository<KilosDisp , Long > {

    @Query("""
            SELECT k.cantidadDisponible
                FROM KilosDisp k JOIN TipoAlimento ta ON k.tipoAlimento.id = ta.id
                WHERE ta.id = ?1
            """)
    Double getKilosDispOfAlimById(Long id);
}
