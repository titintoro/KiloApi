package com.salesianostriana.dam.kiloapi.tiene;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
public class TienePK implements Serializable {

        private final long serialVersionUID = 8682909319466153524L;

       private long caja_id;
       private long tipoAlimento_id;
}
