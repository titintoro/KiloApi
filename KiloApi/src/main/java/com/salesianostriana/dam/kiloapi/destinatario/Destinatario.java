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
    private List<Caja> listaCajas = new ArrayList<>();

<<<<<<< HEAD
    public Destinatario(String direccion, String nombre, String telefono, String personaContacto) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.personaContacto = personaContacto;
=======
    @PreRemove
    public void setNullAlumnos() {
       listaCajas.forEach((caja -> caja.setDestinatario(null)));
>>>>>>> main
    }
}
