package com.salesianostriana.dam.kiloapi.caja.dtos;


import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import com.salesianostriana.dam.kiloapi.tiene.Tiene;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class CreateCajaRequest {

    private int numCaja;
    private String qr;

}
