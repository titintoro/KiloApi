package com.salesianostriana.dam.kiloapi.destinatario;

import com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario.GetDestinatarioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinatarioRepositorio extends JpaRepository<Destinatario, Long> {

    @Query("select dt.nombre,dt.direccion,dt.persona_contacto,dt.telefono, SUM(c.kilos_totales), COUNT(c.num_caja) from Destinatario dt JOIN Caja c ON" +
            "(dt.id = c.destinatario_id ) WHERE dt.id : = idDestinatario GROUP BY dt.nombre,dt.direccion,dt.persona_contacto,dt.telefono;")
    public Optional<GetDestinatarioDto> getDestinatarioById(@Param("idDestinatario") Long id);

    @Query("select dt.nombre,dt.direccion,dt.persona_contacto,dt.telefono, SUM(c.kilos_totales), COUNT(c.num_caja) from Destinatario dt JOIN Caja c ON " +
            "(dt.id = c.destinatario_id ) GROUP BY dt.nombre,dt.direccion,dt.persona_contacto,dt.telefono;")
    public List<GetDestinatarioDto> getDestinatarios();
}
