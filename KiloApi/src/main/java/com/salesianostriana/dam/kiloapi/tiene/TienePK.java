package com.salesianostriana.dam.kiloapi.tiene;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class TienePK implements Serializable {

        private final long serialVersionUID = 8682909319466153524L;

        long caja_id;
        long tipoAlimento_id;
}
