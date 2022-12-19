package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CajaRepositorio extends JpaRepository<Caja, Long> {

    /*
    @Query("""
            SELECT
            """)
    List<TipoAlimento> getTipoALimentoByIdCaja();
    */
}
