package com.salesianostriana.dam.kiloapi.destinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
import com.salesianostriana.dam.kiloapi.destinatario.dtosDestinatario.*;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimentoServicio;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Artist",description = "Este es el controlador de los artistas")
public class DestinatarioControlador {

    private final DestinatarioServicio servicio;

    private final TipoAlimentoServicio servicioTipoAlimento;

    private final DestinatarioConverter dtoConverter;
    private final CajaServicio servicioCaja;

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
    public ResponseEntity<List<GetDestinatarioDto>> findAllDestinatarios(){
        List<GetDestinatarioDto> result = new ArrayList<>();
        for (Destinatario destinatario : servicio.findAll()){
            result.add(dtoConverter.of(destinatario));
        }
        if (result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok().body(result);
        }
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
    public ResponseEntity<GetDestinatarioDtoById> findByIdDestinatarios(@PathVariable Long id){
        Destinatario destinatario = servicio.findById(id).orElse(null);

        if (destinatario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.of(Optional.of(dtoConverter.destinatarioToGetDestinatarioDtoById(destinatario)));
        }

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
    public ResponseEntity<GetDestinatarioDetalleDto> findByIdDestinatarioDetalle(@PathVariable Long id){
        Destinatario destinatario = servicio.findById(id).orElse(null);
        TipoAlimento tipoAlimento = servicioTipoAlimento.findById(id).orElse(null);

        if (destinatario == null && tipoAlimento == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.of(Optional.of(dtoConverter.destinatarioDetalleToDestinatarioDetalleDto(destinatario,tipoAlimento)));
        }
    }

    @Operation(summary = "Este método agrega un destinatario a una lista de destinatarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han agregado un destinatario a la lista de destinatarios",
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
                    description = "No se ha podido agregar un destinatario a la lista de destinatarios",
                    content = @Content),
    })
    @PostMapping("/destinatario/")
    public ResponseEntity<CreateDestinatarioDto> createDestinatario(@RequestBody CreateDestinatarioDto cd){
        Destinatario d = dtoConverter.of(cd);
        servicio.add(d);


        return ResponseEntity.status(HttpStatus.CREATED).body(cd);
    }

    @Operation(summary = "Este método modifica de una lista de destinatarios un destinatario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha modificado un destinatario por su id",
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
                    description = "No se ha modificado ningun destinatario por su id",
                    content = @Content),
    })
    @PutMapping("/destinatario/{id}")
    public ResponseEntity<Destinatario> editDestinatario(@RequestBody Destinatario destinatario, @PathVariable Long id){
        return ResponseEntity.of(servicio.findById(id)
                .map(old -> {
                    old.setNombre(destinatario.getNombre());
                    old.setDireccion(destinatario.getDireccion());
                    old.setTelefono(destinatario.getTelefono());
                    old.setPersonaContacto(destinatario.getPersonaContacto());
                    return Optional.of(servicio.edit(old));
                })
                .orElse(Optional.empty())
        );

    }

    @Operation(summary = "Este método elimina un destinario por su id de una lista de destinatario")
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
                    description = "No se ha eliminado un destinatario por su id",
                    content = @Content),
    })
    @DeleteMapping("/destinatario/{id}")
    public ResponseEntity<Destinatario> deleteDestinatario(@PathVariable Long id){
        List<Caja> listaCaja = servicioCaja.findAll();
        for (Caja caja: listaCaja){
            if (caja.getId() == id){
                caja.setDestinatario(null);
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
