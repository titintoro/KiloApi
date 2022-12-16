package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.caja.dtos.CreateCajaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "CajaController", description = "Caja Controller Class")
public class CajaControlador {

    private final CajaServicio cajaServicio;
    @Operation(summary = "Get a list of Cajas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cajas Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {

                                                }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Cajas Found",
                    content = @Content),
    })
    @GetMapping("/caja")
    public ResponseEntity<List<Caja>> getListOfCajas() {

        return (cajaServicio.findAll().isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(cajaServicio.findAll()));
    }


    @Operation(summary = "Get a single Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Caja Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                  "id": 1,
                                                  "name": "Joaquín Sabina"
                                                }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Caja Found",
                    content = @Content),
    })
    @GetMapping("/caja/{id}")
    public ResponseEntity<Caja> getCaja(@PathVariable Long id) {

        return(cajaServicio.findById(id).isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(cajaServicio.findById(id).get()));
    }


    @Operation(summary = "Create a new Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Caja Created Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                  "id": 14,
                                                  "name": "Joaquín bunny"
                                                }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Caja Creation Request",
                    content = @Content),
    })
    @PostMapping("/caja/")
    public ResponseEntity<Caja> create(@RequestBody CreateCajaRequest createCajaRequest) {

        Caja cajaResponse = createCajaRequest.createCajaRequestToCaja(createCajaRequest, cajaServicio.findListaTiene());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cajaServicio.add(cajaResponse));
    }


    @Operation(summary = "Update a Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Caja Created Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                    {
                                                        "id": 12,
                                                        "name": "Random",
                                                        "description": "Una lista muy loca",
                                                        "songs": 4
                                                    }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Caja Creation Request",
                    content = @Content),
    })
    @PutMapping("/caja/{id}")
    public ResponseEntity<Caja> edit(
            @RequestBody CreateCajaRequest c,
            @PathVariable Long id) {

        if (cajaServicio.findById(id).isEmpty())
            return ResponseEntity.notFound().build();


        return ResponseEntity.of(
                cajaServicio.findById(id).map(m -> {

                    m.setQr(c.getQr());
                    m.setNumCaja(c.getNumCaja());

                    return c.createCajaRequestToCaja(c, cajaServicio.findListaTiene());
                }));

    }


    @Operation(summary = "Delete an Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Caja Deleted Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class))
                    )}),
    })
    @DeleteMapping("/caja/{id}")
    public ResponseEntity<?> deleteCaja(@PathVariable Long id) {

        if(cajaServicio.existsById(id))
                cajaServicio.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

    @Operation(summary = "Delete an Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Caja Deleted Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class))
                    )}),
    })
    @DeleteMapping("/caja/{id}/tipo/{idTipoAlim}")
    public ResponseEntity<?> deleteAlimFromCaja(@PathVariable Long id, Long idTipoAlim) {

        

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

}
