package com.salesianostriana.dam.kiloapi.destinatario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinatarioRepositorio extends JpaRepository<Destinatario, Long> {

    @Query("select dt.nombre,dt.direccion,dt.personaContacto,dt.telefono,c.kilosTotales from Destinatario dt JOIN Caja c ON  (dt.id = c.destinatario) where c.destinatario = :idDestinatario ")
    public void getDestinatarios(@Param("idDestinatario") Long id);
}
