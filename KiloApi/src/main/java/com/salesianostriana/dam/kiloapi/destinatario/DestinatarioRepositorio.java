package com.salesianostriana.dam.kiloapi.destinatario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinatarioRepositorio extends JpaRepository<Destinatario, Long> {
}
