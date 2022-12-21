package com.salesianostriana.dam.kiloapi.clase;


import com.salesianostriana.dam.kiloapi.clase.dto.ClaseResponse;
import com.salesianostriana.dam.kiloapi.tipoAlimento.dto.TipoAlimentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clase")
@Tag(name = "Clase", description = "Controlador encargado de gestionar todo lo relacionado con las clases del colegio")
public class ClaseControlador {

    private final ClaseService claseService;

    @Operation(summary = "Lista todas las clases del centro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Clases listadas con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado clases",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<ClaseResponse>> showAllClases() {

        List<Clase> data = claseService.findAll();
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

            List<ClaseResponse> results = data.stream()
                                                .map(ClaseResponse::convertClaseToClaseResponse)
                                                    .toList();

            return ResponseEntity.ok().body(results);
        }


    }

    @Operation(summary = "Muestra la información de una clase buscada por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Muestra la clase buscada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la clase",
                    content = @Content),

    })
    @GetMapping("/{id}")
    public ResponseEntity<ClaseResponse> getOneClaseInfo(@PathVariable Long id) {

        Optional<Clase> c = claseService.findById(id);

        if (c.isPresent())
            return ResponseEntity.notFound().build();

        ClaseResponse result = ClaseResponse.convertClaseToClaseResponse(c.get());

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Crea una clase con los atributos proporcionados en el cuerpo de la petición")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva clase",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear la clase",
                    content = @Content),

    })
    @PostMapping("/")
    public ResponseEntity<ClaseResponse> addOneClase(@RequestBody Clase clase) {
        claseService.add(clase);
        ClaseResponse result = ClaseResponse.convertClaseToClaseResponse(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Actualiza una clase con los atributos proporcionados por el cuerpo de la petición")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado con éxito la clase",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la clase",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClaseResponse> updateClase(@PathVariable Long id, @RequestBody Clase clase) {


        Optional<Clase> c = claseService.findById(id);

        if (!c.isPresent() || clase == null)
            return ResponseEntity.badRequest().build();

        c.get().setNombre(clase.getNombre());
        c.get().setTutor(clase.getTutor());
        c.get().setListaAportaciones(clase.getListaAportaciones());

        claseService.edit(c.get());

        ClaseResponse result = ClaseResponse.convertClaseToClaseResponse(clase);


        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Borra una clase buscada por el id, pasado por un query param")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Clase borrada con exito",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClase(@PathVariable Long id) {
        Clase c = claseService.findById(id).orElse(null);
        if (c == null)
            return ResponseEntity.notFound().build();
        claseService.delete(c);
        return ResponseEntity.noContent().build();
    }


}
