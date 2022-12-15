package com.salesianostriana.dam.kiloapi.clase;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter @Setter
public class Clase {

    @GeneratedValue @Id
    private Long idClase;

    private String tutor;

    private String nombre;

    @OneToMany(mappedBy = "clase")
    private List<Aportacion> listaAportaciones = new ArrayList<>();





}
