package com.salesianostriana.dam.kiloapi.destinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Destinatario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre, direccion, personaContacto, telefono;

    @ToString.Exclude
    @OneToMany(mappedBy = "destinatario", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Caja> listaDeCajas = new ArrayList<>();


    public Destinatario(String direccion, String nombre, String telefono, String personaContacto) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.personaContacto = personaContacto;
    }

    public List<Long> listaIdDeCaja(){
        List<Caja> cajas = listaDeCajas;
        List<Long> result = new ArrayList<>();
        for (Caja caja : cajas){
            result.add(caja.getId());
        }
        return result;

    }

    public List<Double> listaKilosTotales(){
        List<Caja> cajas = listaDeCajas;
        List<Double> result = new ArrayList<>();
        for (Caja caja : cajas){
            result.add(caja.getKilosTotales());
        }
        return result;
    }

    @PreRemove
    public void setNullAlumnos() {
        listaDeCajas.forEach((caja -> caja.setDestinatario(null)));
    }
}

