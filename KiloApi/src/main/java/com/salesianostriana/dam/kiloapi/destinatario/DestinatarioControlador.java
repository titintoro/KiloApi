package com.salesianostriana.dam.kiloapi.destinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Artist",description = "Este es el controlador de los artistas")
public class DestinatarioControlador {

    private DestinatarioServicio servicio;
    private CajaServicio servicioCaja;

    @Operation(summary = "Este método devuelve una lista de destinatarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de destinatarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Destinatario.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                            
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna lista de destinatarios",
                    content = @Content),
    })
    @GetMapping("/destinatario/")
    public ResponseEntity<List<Destinatario>> findAllDestinatarios(){
        return ResponseEntity.ok(servicio.findAll());
    }

    @Operation(summary = "Este método devuelve una lista de destinatarios por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la lista de destinatarios por su id",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Destinatario.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                            
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna lista de destinatarios por su id",
                    content = @Content),
    })
    @GetMapping("/destinatario/{id}")
    public ResponseEntity<Destinatario> findByIdDestinatarios(@PathVariable Long id){
        return ResponseEntity.of(servicio.findById(id));
    }

    @Operation(summary = "Este método devuelve los detalles una lista de destinatarios por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la lista de reproducción por su id",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Destinatario.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                            
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los detalles de ninguna lista de destinatarios por su id",
                    content = @Content),
    })
    @GetMapping("destinatario/{id}/detalle")
    public ResponseEntity<Destinatario> findByIdDestinatarioDetalle(@PathVariable Long id){
        return ResponseEntity.of(servicio.findById(id));
    }

    @Operation(summary = "Este método devuelve una lista de reproducción por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la lista de reproducción por su id",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Destinatario.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                            
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los detalles de  ninguna lista de reproducción por su id",
                    content = @Content),
    })
    @PostMapping("/destinatario/")
    public ResponseEntity<Destinatario> createDestinatario(@RequestBody Destinatario destinatario){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.add(destinatario));
    }

    @PutMapping("/destinatario/{id}")
    public ResponseEntity<Destinatario> editDestinatario(@RequestBody Destinatario destinatario, @PathVariable Long id){
        return ResponseEntity.of(servicio.findById(id)
                .map(old -> {
                    old.setNombre(destinatario.getNombre());
                    old.setDireccion(destinatario.getDireccion());
                    old.setTelefono(destinatario.getTelefono());
                    old.setPersonaContacto(destinatario.getPersonaContacto());
                    old.setListaCajas(destinatario.getListaCajas());
                    return Optional.of(servicio.edit(old));
                })
                .orElse(Optional.empty())
        );
    }

    @DeleteMapping("/destinatario/{id}")
    public ResponseEntity<Destinatario> deleteDestinatario(@RequestBody Destinatario destinatario, @PathVariable Long id){
        List<Caja> listaCaja = servicioCaja.findAll();
        for (Caja caja: listaCaja){
            if (caja.getId() == id){
                caja.setDestinatario(null);
            }
        }
        servicio.delete(destinatario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
