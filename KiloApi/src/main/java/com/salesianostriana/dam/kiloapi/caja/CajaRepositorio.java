package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CajaRepositorio extends JpaRepository<Caja, Long> {


    @Query("select t from Tiene t ")
    List<Tiene> findListOFTiene();

    List<Tiene> findByIdCaja(Long idCaja);

    @Query("select t from Tiene t where t.firstname = :firstname")
    List<Tiene> findByFirstname(String firstname);

}
