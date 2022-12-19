package com.salesianostriana.dam.kiloapi.clase;


import com.salesianostriana.dam.kiloapi.clase.dto.ClaseResponse;
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
    public ResponseEntity<List<Clase>> showAllClases() {

        List<Clase> data = claseService.findAll();
        if (data.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(data);
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

            if(!c.isPresent())
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
    public ResponseEntity<Clase> addOneClase(@RequestBody Clase clase) {
        claseService.add(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(clase);
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
    public ResponseEntity<Clase> updateClase(@PathVariable Long id, @RequestBody Clase clase) {



        Optional<Clase> c = claseService.findById(id);

        if (!c.isPresent() || clase == null)
            return ResponseEntity.badRequest().build();

        c.get().setNombre(clase.getNombre());
        c.get().setTutor(clase.getTutor());
        c.get().setListaAportaciones(clase.getListaAportaciones());

        claseService.edit(c.get());

        return ResponseEntity.ok().body(c.get());
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
        if(c  == null)
            return ResponseEntity.notFound().build();
        claseService.delete(c);
        return ResponseEntity.noContent().build();
    }


}
