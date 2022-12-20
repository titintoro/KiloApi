package com.salesianostriana.dam.kiloapi.tipoAlimento;

import com.salesianostriana.dam.kiloapi.tipoAlimento.dto.TipoAlimentoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAlimentoRepository extends JpaRepository<TipoAlimento, Long> {
/*
    @Query("""
            SELECT new com.salesianostriana.dam.kiloapi.tipoAlimento.dto.TipoAlimentoResponse (tp.nombre , tp.id)
            FROM TipoAlimento tp
            WHERE tp.id = :idTipoAlimento
           """
    )
    TipoAlimentoResponse consultarInfoUnTipoAlimento(@Param("idTipoAlimento") Long id);
*/
}
