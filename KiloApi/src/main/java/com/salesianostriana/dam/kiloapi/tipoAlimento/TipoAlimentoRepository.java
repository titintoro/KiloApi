package com.salesianostriana.dam.kiloapi.tipoAlimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAlimentoRepository extends JpaRepository<TipoAlimento, Long> {
}
