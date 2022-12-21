package com.salesianostriana.dam.kiloapi.tipoAlimento;

import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import com.salesianostriana.dam.kiloapi.tipoAlimento.dto.TipoAlimentoRequest;
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

@RestController
@RequestMapping("/tipoAlimento")
@RequiredArgsConstructor
@Tag(name = "TipoAlimento", description = "Controlador encargado de gestionar todo lo relacionado con los distintos tipos de alimentos donados")
public class TipoAlimentoControlador {

    private final TipoAlimentoServicio tipoAlimentoServicio;

    private final CajaServicio cajaServicio;

    private final KilosDispService kilosDispService;


    @Operation(summary = "Lista todas las tipos de alimentos guardados en la bbdd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tipos de alimentos listados con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado alimentos",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<TipoAlimentoResponse>> listAllTipoAlimento() {
        List<TipoAlimento> data = tipoAlimentoServicio.findAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();

        } else {
            List<TipoAlimentoResponse> results = data.stream()
                    .map(TipoAlimentoResponse::convertTipoAlimentoToResponse)
                    .toList();

            return ResponseEntity.ok().body(results);
        }


    }

    @Operation(summary = "Lista un tipo de alimento buscado por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el alimento y lo muestra",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún alimento",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimentoResponse> getOneTipoAlimentoInfo(@PathVariable Long id) {

        TipoAlimentoResponse result = tipoAlimentoServicio.getOneTipo(id);

        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }

    @Operation(summary = "Crea un alimento con los atributos proporcionados en el cuerpo de la petición")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un nuevo alimento",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear el alimento",
                    content = @Content),

    })
    @PostMapping("/")
    public ResponseEntity<TipoAlimentoResponse> addOneTipoAlimento(@RequestBody TipoAlimento tipoAlimento) {

        if (tipoAlimento.getNombre().isEmpty())
            return ResponseEntity.badRequest().build();

        tipoAlimentoServicio.add(tipoAlimento);

        TipoAlimentoResponse t = TipoAlimentoResponse.convertTipoAlimentoToResponse(tipoAlimento);

        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @Operation(summary = "Modifica un alimento con los atributos proporcionados en el cuerpo de la petición")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva clase",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clase.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido modificar el alimento",
                    content = @Content),

    })

    @PutMapping("/{id}")
    public ResponseEntity<TipoAlimentoResponse> updateOneTipoAlimento(@RequestBody TipoAlimento tipoAlimentoPasado, @PathVariable Long id) {
        Optional<TipoAlimento> tipoAlimento = tipoAlimentoServicio.findById(id);

        if (tipoAlimento.isEmpty())
            return ResponseEntity.badRequest().build();

        tipoAlimento.get().setNombre(tipoAlimentoPasado.getNombre());

        tipoAlimentoServicio.edit(tipoAlimento.get());

        TipoAlimentoResponse t = TipoAlimentoResponse.convertTipoAlimentoToResponse(tipoAlimento.get());

        return ResponseEntity.ok(t);

    }
    @Operation(summary = "Borra un alimento buscado por el id, pasado por un query param")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Alimento borrada con exito",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoAlimento(@PathVariable Long id) {
        Optional<TipoAlimento> aux = tipoAlimentoServicio.findById(id);

        if (tipoAlimentoServicio.checkCantidad(aux).getTieneList().isEmpty()) {
            tipoAlimentoServicio.delete(aux.get());

        } else {
            tipoAlimentoServicio.checkCantidad(aux).getTieneList()
                    .stream()
                    .filter(tn -> tn.getTipoAlimento().equals(aux.get()))
                    .findFirst().get().setTipoAlimento(null);
            tipoAlimentoServicio.delete(aux.get());
        }
        return ResponseEntity.noContent().build();
    }

}