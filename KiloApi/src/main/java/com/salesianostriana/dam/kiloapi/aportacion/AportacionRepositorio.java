package com.salesianostriana.dam.kiloapi.aportacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AportacionRepositorio extends JpaRepository<Aportacion, Long> {

}
