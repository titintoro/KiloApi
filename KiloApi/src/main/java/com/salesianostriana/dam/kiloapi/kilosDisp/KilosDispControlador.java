package com.salesianostriana.dam.kiloapi.kilosDisp;

import com.salesianostriana.dam.kiloapi.kilosDisp.dto.GetKilosDispDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.kilosDisp.dto.GetKilosDispDto;
import com.salesianostriana.dam.kiloapi.kilosDisp.dto.GetKilosDispDtoById;
import com.salesianostriana.dam.kiloapi.kilosDisp.dto.KilosDispConverter;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class KilosDispControlador {

    private final KilosDispService service;

    private final KilosDispConverter dtoConverter;

    @Operation(summary = "Este método devuelve el listado de la lista de kilos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de kilos disponibles",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = KilosDisp.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido encontrar ninguna lista de kilos disponibles",
                    content = @Content),
    })
    @GetMapping("/kilosDisponibles/")
    public ResponseEntity<List<GetKilosDispDto>> findAllKilosDisp(){
        List<GetKilosDispDto> result = new ArrayList<>();
        for (KilosDisp kilosDisp : service.findAll()){
            result.add(dtoConverter.of(kilosDisp));
        }
        if(result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok().body(result);
        }
    }

    @Operation(summary = "Este método devuelve el listado de un tipo alimento en una lista de kilos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el tipo de alimento en la lista de kilos disponibles",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = KilosDisp.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido encontrar el listado de un tipo alimento en una lista de kilos disponibles",
                    content = @Content),
    })
    @GetMapping("/kilosDisponibles/{idTipoAlimento}")
    public ResponseEntity<GetKilosDispDtoById> findByIdKilosDisp(@PathVariable Long idTipoAlimento){
       KilosDisp kilosDisp = service.findById(idTipoAlimento).orElse(null);

        if (kilosDisp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.of(Optional.of(dtoConverter.findByIdKilosDisp(kilosDisp)));
        }
    }
}
