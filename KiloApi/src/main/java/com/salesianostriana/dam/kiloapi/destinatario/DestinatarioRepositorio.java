package com.salesianostriana.dam.kiloapi.destinatario;

import com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario.GetDestinatarioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinatarioRepositorio extends JpaRepository<Destinatario, Long> {

    @Query("select dt.nombre,dt.direccion,dt.personaContacto,dt.telefono,c.kilosTotales from Destinatario dt JOIN Caja c ON  (dt.id = c.destinatario) where c.destinatario = :idDestinatario ")
    public List<GetDestinatarioDto> getDestinatarios(@Param("idDestinatario") Long id);
}
