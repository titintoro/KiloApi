package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Caja {

    @Id
    @GeneratedValue
    private Long id;

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


    public void addToDestinatario(Destinatario d) {
        this.destinatario = d;
        d.getListaDeCajas().add(this);
    }


    public void removeFromDestinatario(Destinatario d) {
        this.destinatario = null;
        d.getListaDeCajas().remove(this);
    }

    @PreRemove
    public void setNullDestinatario() {

        if (destinatario!=null)
            destinatario.getListaDeCajas().removeIf(caja -> caja.equals(this));

    }

}
