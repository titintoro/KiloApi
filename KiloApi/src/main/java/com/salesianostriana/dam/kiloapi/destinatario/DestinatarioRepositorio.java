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

    @Query("select dt.nombre,dt.direccion,dt.personaContacto,dt.telefono,c.kilosTotales,(select COUNT(c2.numCaja) from Caja c2)" +
            "from Destinatario dt JOIN Caja c ON  (dt.id = c.destinatario) where c.destinatario = :idDestinatario ")
    public Optional<GetDestinatarioDto> getDestinatarioById(@Param("idDestinatario") Long id);

    @Query("select dt.nombre,dt.direccion,dt.personaContacto,dt.telefono,c.kilosTotales,(select COUNT(c2.numCaja) from Caja c2)" +
            " from Destinatario dt JOIN Caja c ON  (dt.id = c.destinatario)")
    public List<GetDestinatarioDto> getDestinatarios();
}
