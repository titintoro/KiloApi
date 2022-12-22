package com.salesianostriana.dam.kiloapi.aportacion;

import com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion.*;
import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
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


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Aportación", description = "Este es el controlador de las Aportaciones")
public class AportacionControlador {


    private final AportacionServicio aportacionServicio;
    private final AportacionRepositorio aportacionRepo;
    private final ClaseService claseService;

    private final AportacionDtoConverter aportacionDtoConverter;



    private final KilosDispService kilosDispService;

    private final TipoAlimentoServicio tipoAlimentoServicio;






    private final AportacionDtoConverter dtoConverter;

    @Operation(summary = "Petición que devuelve los datos de todas las Aportaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las Aportaciones",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAportacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 11,
                                                    "fecha": "2022-12-22",
                                                    "nombreClase": "1 DAM",
                                                    "numKilos": 10.0
                                                }
                                            ]                                    
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
                                            {
                                                "details": [
                                                    {
                                                        "numLinea": 1,
                                                        "nombreTipoAlimento": "Macarrones",
                                                        "numKilos": 10.0
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la Aportación específica",
                    content = @Content),
    })
    @GetMapping("/aportacion/{id}")
    public ResponseEntity<GetByIdAportacionDto> findAportacionById(@PathVariable Long id){
        Aportacion aportacion = aportacionServicio.findById(id).orElse(null);
        //return aportacionOptional.map((aportacion) -> {
        //        ResponseEntity.of(GetByIdAportacionDto.of(aportacion))});

        if (aportacion != null)
            return ResponseEntity.of(Optional.of(GetByIdAportacionDto.of(aportacion)));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Operation(summary = "Petición que borra una Aportación proporcionada por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la Aportación",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aportacion.class))}),

    })
    @DeleteMapping("/aportacion/{id}")
    public ResponseEntity<?> deleteAportacion (@PathVariable Long id){
        if (aportacionRepo.existsById(id))
            aportacionRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "Petición que obtiene los Detalles de una Aportación de una Clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la Aportación",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aportacion.class),
                            examples = {@ExampleObject(
                                    value = """
                                            Clase= 1 Fecha= 2022-12-22,Aportaciones=[
                                                [Macarrones,
                                                    10.0
                                                ],
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado los Detalles de Aportacion de la Clase",
                    content = @Content),
    })
    @GetMapping("/aportacion/clase/{idClase}")
    public ResponseEntity<String> detallesAportacionPorClase(@PathVariable Long idClase){
        String res="";
        Clase claseSeleccionada =claseService.findById(idClase).get();

        List<Aportacion>ListaAportaciones=claseSeleccionada.getListaAportaciones();

        for(Aportacion aportacion:ListaAportaciones){

            LocalDate fecha=aportacion.getFecha();
            List<DetalleAportacion>ListaDetallesAportacion=aportacion.getDetalleAportacionList();

            res=res+"Clase= "+idClase.toString()+" Fecha= "+((LocalDate) fecha).toString()+",Aportaciones=[";
            for(DetalleAportacion detalles:ListaDetallesAportacion){
                String nombre=detalles.getTipoAlimento().getNombre();
                Double kilos=detalles.getCantidad();
                res=res+"["+nombre+","+kilos+"],";
            }

            res=res+"]";
            ;
        }


        if(res!=""){
            return ResponseEntity.ok().body(res);
        }else{
            return ResponseEntity.notFound().build();
        }


    }

    @Operation(summary = "Petición que borra los Detalles de una Aportación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado el Detalle de la Aportación",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aportacion.class))}),

    })
    @DeleteMapping("/aportacion/{id}/linea/{num}")
    public ResponseEntity<AportacionResponseDto> deleteDetalleAportacion (@PathVariable("id") Long id,@PathVariable("num") Long numLinea){
            aportacionServicio.removeDetalle(id, numLinea);
            return ResponseEntity.ok().build();

    }

    @Operation(summary = "Petición que agrega una nueva Aportación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha agregado la nueva Aportación",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNuevaAportacionDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 11,
                                                "nombreClase": "1 DAM",
                                                "fecha": "2022-12-21",
                                                "listaDetalles": [
                                                    {
                                                        "numLinea": 1,
                                                        "nombreTipoAlimento": "Macarrones",
                                                        "numeroKilos": 10.0
                                                    }
                                                ]
                                            }                                      
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha agregado la nueva Aportación",
                    content = @Content),
    })
    @PostMapping("/aportacion/")
    public ResponseEntity<GetNuevaAportacionDto> createAportacion(@RequestBody AportacionResponseDto dto){
        if (dto.getFecha() == null || dto.getDetalles() == null || dto.getDetalles().size() == 0 || dto.getIdClase() == null)
            return ResponseEntity.badRequest().build();

        Aportacion nuevaAportacion = aportacionServicio.createAportacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aportacionDtoConverter.nuevaAportacionDto(nuevaAportacion));

    }



}
