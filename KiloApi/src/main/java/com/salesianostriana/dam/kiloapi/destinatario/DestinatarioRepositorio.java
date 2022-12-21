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

    @Query("select dt.nombre,dt.direccion,dt.personaContacto,dt.telefono, SUM(c.kilosTotales), COUNT(c.numCaja) from Destinatario dt JOIN Caja c ON" +
            "(dt.id = c.destinatario ) WHERE dt.id  = : idDestinatario GROUP BY dt.nombre,dt.direccion,dt.personaContacto,dt.telefono")
    public Optional<GetDestinatarioDto> getDestinatarioById(@Param("idDestinatario") Long id);

    @Query("select dt.nombre,dt.direccion,dt.personaContacto,dt.telefono, SUM(c.kilosTotales), COUNT(c.numCaja) from Destinatario dt JOIN Caja c ON " +
            "(dt.id = c.destinatario ) GROUP BY dt.nombre,dt.direccion,dt.personaContacto,dt.telefono")
    public List<GetDestinatarioDto> getDestinatarios();
}
