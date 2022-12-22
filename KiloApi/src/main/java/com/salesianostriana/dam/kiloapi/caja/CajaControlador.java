package com.salesianostriana.dam.kiloapi.caja;

import com.salesianostriana.dam.kiloapi.caja.dtos.*;
import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import com.salesianostriana.dam.kiloapi.destinatario.DestinatarioServicio;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "CajaController", description = "Caja Controller Class")
public class CajaControlador {

    private final CajaServicio cajaServicio;
    private final CajaDtoConverter cajaDtoConverter;
    private final DestinatarioServicio destinatarioServicio;

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
                                                      "id": 11,
                                                      "qr": "aaaaaaa",
                                                      "numCaja": 1,
                                                      "kilosTotales": 0.0,
                                                      "destinatario": null,
                                                      "tieneList": []
                                                  },
                                                  {
                                                      "id": 12,
                                                      "qr": "bbbbbb",
                                                      "numCaja": 2,
                                                      "kilosTotales": 0.0,
                                                      "destinatario": null,
                                                      "tieneList": []
                                                  }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Cajas Found",
                    content = @Content),
    })
    @GetMapping("/caja/")
    public ResponseEntity<List<GetCajaResponse>> getListOfCajas() {

        List<GetCajaResponse> cajaResponseList = new ArrayList<>();

        for (Caja caja : cajaServicio.findAll()){
            cajaResponseList.add(cajaDtoConverter.toGetCajaResponse(caja, caja.getDestinatario()));
        }

        return (cajaServicio.findAll().isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(cajaResponseList));
    }


    @Operation(summary = "Get a single Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Caja Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "numCaja": 1,
                                                "qr": "aaaaaaa",
                                                "listaAlimentos": [],
                                                "destinatario": null,
                                                "kilosTotales": 0.0
                                            }                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Caja Found",
                    content = @Content),
    })
    @GetMapping("/caja/{id}")
    public ResponseEntity<GetCajaResponse> getCaja(@PathVariable Long id) {

        return(cajaServicio.findById(id).isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(cajaDtoConverter.toGetCajaResponse(cajaServicio.findById(id).get(),cajaServicio.findById(id).get().getDestinatario())));
    }


    @Operation(summary = "Create a new Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Caja Created Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 12,
                                                "qr": "bbbbbb",
                                                "numCaja": 2,
                                                "kilosTotales": 0.0,
                                                "destinatario": null,
                                                "tieneList": []
                                            }                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Caja Creation Request",
                    content = @Content),
    })
    @PostMapping("/caja/")
    public ResponseEntity<GetCajaResponse> createCaja(@RequestBody CreateCajaRequest createCajaRequest) {

        Caja cajaResponse = cajaDtoConverter.createCajaRequestToCaja(createCajaRequest);
        cajaServicio.add(cajaResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cajaDtoConverter.toGetCajaResponse(cajaResponse, cajaResponse.getDestinatario()));
    }


    @Operation(summary = "Add TipoAlimento to Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "TipoAlimento added Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "numCaja": 12,
                                                "qr": "qqqq",
                                                "listaAlimentos": [
                                                    {
                                                        "id": 8,
                                                        "nombre": "Garbanzos",
                                                        "cantidadKgs": 3.0
                                                    }
                                                ],
                                                "destinatario": {
                                                    "id": 15,
                                                    "nombre": "Ale"
                                                },
                                                "kilosTotales": 3.0
                                            }                                        \s
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Caja TipoAlimento add Request",
                    content = @Content),
    })
    @PostMapping("/caja/{id}/tipo/{idTipoAlim}/kg/{cantidad}")
    public ResponseEntity<PostCajaAlimentoResponse> addTipoAlimToCaja(
            @PathVariable Long id , @PathVariable Long idTipoAlim, @PathVariable double cantidad) {

        if (id!=null || idTipoAlim != null || cantidad >0){
            Optional<Caja> c = cajaServicio.addAlimToCaja(id,idTipoAlim,cantidad);

            return (c.map(caja -> ResponseEntity.status(HttpStatus.CREATED).body(cajaDtoConverter.toPostCajaAlimentoResponse(caja))).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }


    @Operation(summary = "Update a Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Caja updated Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                    {
                                                        "numCaja": 12,
                                                        "qr": "qqqaa",
                                                        "listaAlimentos": [],
                                                        "destinatario": null,
                                                        "kilosTotales": 0.0
                                                    }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Caja update Request",
                    content = @Content),
    })
    @PutMapping("/caja/{id}")
    public ResponseEntity<GetCajaResponse> edit(
            @RequestBody EditCajaRequest c,
            @PathVariable Long id) {

        if (cajaServicio.findById(id).isEmpty())
            return ResponseEntity.badRequest().build();


        return ResponseEntity.of(
                cajaServicio.findById(id).map(m -> {
                    m.setQr(c.getQr());
                    m.setNumCaja(c.getNumCaja());
                    m.setDestinatario(destinatarioServicio.findById(c.getIdDestinatario()).get());
                    Caja caja = cajaServicio.edit(cajaDtoConverter.editCajaRequestToCaja(c, destinatarioServicio.findById(c.getIdDestinatario()).get()));
                    return cajaDtoConverter.toGetCajaResponse(caja, caja.getDestinatario());
                }));

    }


    @Operation(summary = "Add Destinatario to Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Destinatario added Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "numCaja": 12,
                                                "qr": "qqqq",
                                                "listaAlimentos": [],
                                                "destinatario": {
                                                    "id": 15,
                                                    "nombre": "Ale"
                                                },
                                                "kilosTotales": 0.0
                                            }                                        \s
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Destinatario add Request",
                    content = @Content),
    })
    @PostMapping("/caja/{idCaja}/destinatario/{idDestinatario}")
    public ResponseEntity<PostCajaAlimentoResponse> addDestinatarioToCaja(
            @PathVariable Long idCaja, @PathVariable Long idDestinatario) {

        if (idCaja!=null&&idDestinatario!=null) {
            Optional<Caja> c = cajaServicio.findById(idCaja);
            Optional<Destinatario> d = destinatarioServicio.findById(idDestinatario);

            if (c.isPresent() && d.isPresent()) {

                c.get().addToDestinatario(d.get());
                cajaServicio.edit(c.get());

                return ResponseEntity.status(HttpStatus.CREATED).body(cajaDtoConverter.toPostCajaAlimentoResponse(c.get()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }




    @Operation(summary = "Update a TipoAlimento of Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "TipoAlimento updated Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                    {
                                                        "numCaja": 12,
                                                        "qr": "qqqaa",
                                                        "listaAlimentos": [
                                                            {
                                                                "id": 8,
                                                                "nombre": "Garbanzos",
                                                                "cantidadKgs": 2.0
                                                            }
                                                        ],
                                                        "destinatario": {
                                                            "id": 15,
                                                            "nombre": "Ale"
                                                        },
                                                        "kilosTotales": 2.0
                                                    }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad TipoAlimento of Caja update Request",
                    content = @Content),
    })
    @PutMapping("/caja/{id}/tipo/{idTipoAlim}/kg/{cantidad}")
    public ResponseEntity<GetCajaResponse> editKgsOFTipoAlimFromCaja(
            @PathVariable Long id, @PathVariable Long idTipoAlim, @PathVariable double cantidad) {

        Optional<Caja> caja = cajaServicio.updateKgsOfTipoAlimentoFromCaja(id,idTipoAlim,cantidad);

        return (caja.map(value -> ResponseEntity.ok(cajaDtoConverter.toGetCajaResponse(value,value.getDestinatario()))).orElseGet(() -> ResponseEntity.badRequest().build()));

    }


    @Operation(summary = "Delete a Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Caja Deleted Successfully"
                    ),
    })
    @DeleteMapping("/caja/{id}")
    public ResponseEntity<?> deleteCaja(@PathVariable Long id) {

        if(cajaServicio.existsById(id))
                cajaServicio.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


    @Operation(summary = "Delete a TipoAlimento of Caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "TipoAlimento Deleted Successfully of Caja",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "numCaja": 12,
                                                 "qr": "qqqaa",
                                                 "listaAlimentos": [],
                                                 "destinatario": {
                                                     "id": 15,
                                                     "nombre": "Ale"
                                                 },
                                                 "kilosTotales": 0.0
                                             }                                          
                                            """
                            )}
                    )}),
    })
    @DeleteMapping("/caja/{id}/tipo/{idTipoAlim}")
    public ResponseEntity<GetCajaResponse> deleteAlimFromCaja(@PathVariable Long id, @PathVariable Long idTipoAlim) {

        Caja c = cajaServicio.deleteAlimFromCaja(id,idTipoAlim);
        return ResponseEntity.ok(cajaDtoConverter.toGetCajaResponse(c,c.getDestinatario()));

    }

}
