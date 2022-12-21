package com.salesianostriana.dam.kiloapi.kilosDisp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KilosDispRepository extends JpaRepository<KilosDisp , Long > {

    @Query("""
            SELECT k.cantidadDisponible
                FROM KilosDisp k JOIN TipoAlimento ta ON k.tipoAlimento.id = ta.id
                WHERE ta.id = ?1
            """)
    double getKilosDispOfAlimById(Long id);
}
