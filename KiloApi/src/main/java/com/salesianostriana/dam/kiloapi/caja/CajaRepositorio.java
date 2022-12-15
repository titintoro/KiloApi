package com.salesianostriana.dam.kiloapi.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepositorio extends JpaRepository<Caja, Long> {

}
