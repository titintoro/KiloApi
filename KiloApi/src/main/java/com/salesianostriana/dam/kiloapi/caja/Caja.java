package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Caja {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String qr;

    private int numCaja;
    private double kilosTotales;

    @ManyToOne
    @JoinColumn(name = "destinatario_id",
            foreignKey = @ForeignKey(name="DESTINATARIO_ID_FK"))
    private Destinatario destinatario;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "caja", fetch = FetchType.EAGER)
    private List<Tiene> tieneList = new ArrayList<>();

}
