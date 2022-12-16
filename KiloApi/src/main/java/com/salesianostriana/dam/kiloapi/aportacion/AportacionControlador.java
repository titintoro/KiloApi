package com.salesianostriana.dam.kiloapi.aportacion;

import com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion.GetAportacionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Aportación", description = "Este es el controlador de las Aportaciones")
public class AportacionControlador {


    private final AportacionServicio aportacionServicio;

    @Operation(summary = "Petición que devuelve los datos de todas las Aportaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las Aportaciones",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAportacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                                                  
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna Aportacion",
                    content = @Content),
    })
    @GetMapping("/aportacion/")
    public ResponseEntity <List<GetAportacionDto>> findAllAportacion(){
    List<Aportacion> data = aportacionServicio.findAll();
        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List <GetAportacionDto> result = data.stream()
                    .map(GetAportacionDto::of)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(result);
        }
    }

    @Operation(summary = "Petición que obtiene una Aportación especificada por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la Aportación",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aportacion.class),
                            examples = {@ExampleObject(
                                    value = """
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la Aportación específica",
                    content = @Content),
    })
    @GetMapping("/aportacion/{id}")
    public ResponseEntity<Aportacion> findAportacionById(@PathVariable Long id){
        return ResponseEntity.of(aportacionServicio.findById(id));
    }

}
