package com.salesianostriana.dam.kiloapi.aportacion;

import com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion.*;
import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;

import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacionRepositorio;
import com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion.DetalleAportacionResponseDto;
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

    private final DetalleAportacionRepositorio detalleRepo;




    private final AportacionDtoConverter dtoConverter;

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
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la Aportación específica",
                    content = @Content),
    })
    @DeleteMapping("/aportacion/{id}")
    public ResponseEntity<?> deleteAportacion (@PathVariable Long id){
        if (aportacionRepo.existsById(id))
            aportacionRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    @GetMapping("/aportacion/clase/{idClase}")
    public ResponseEntity<String> detallesAportacionPorClase(@PathVariable Long idClase){
        String res="";
        Clase claseSeleccionada =claseService.findById(idClase).get();

        List<Aportacion>ListaAportaciones=claseSeleccionada.getListaAportaciones();

        for(Aportacion aportacion:ListaAportaciones){
            int i=0;
            LocalDate fecha=aportacion.getFecha();
            List<DetalleAportacion>ListaDetallesAportacion=aportacion.getDetalleAportacionList();

            res=res+"Aportacion"+i+": fecha="+((LocalDate) fecha).toString()+",Aportaciones=[";
            for(DetalleAportacion detalles:ListaDetallesAportacion){
                String nombre=detalles.getTipoAlimento().getNombre();
                Double kilos=detalles.getCantidadKilos();
                res=res+"["+nombre+","+kilos+"],";
            }
            res=res+"]";
            i++;
        }
        //Aportacion 1: fecha=2022-2-12 , Aportaciones= [ [patata,22] , [zanahoria,11] ] 1 vuelta
        //Aportacion 1: fecha=2022-2-12 , Aportaciones= [ [patata,22] , [zanahoria,11] ] Aportacion 2: fecha=2022-2-12 , Aportaciones= [ [patata,22] , [zanahoria,11] ]


        if(res!=""){
            return ResponseEntity.ok().body(res);
        }else{
            return ResponseEntity.notFound().build();
        }


    }

    //Preguntar por qué tiene solamente un 200 OK y no un 204 No content
    @DeleteMapping("/aportacion/{id}/linea/{num}")
    public ResponseEntity<Aportacion> deleteAportacion (@PathVariable Long id,@PathVariable Long num,AportacionServicio aportacionServicio){
        /**Integer sizeAntes=null;
        Integer sizeDespues=null;**/

        Aportacion aportacionAeditar=aportacionServicio.findById(id).get();
        List<DetalleAportacion>listaAportaciones=aportacionAeditar.getDetalleAportacionList();
        //sizeAntes=listaAportaciones.size();
        listaAportaciones.remove(num);
        aportacionAeditar.setDetalleAportacionList(listaAportaciones);
        aportacionRepo.save(aportacionAeditar);

        //sizeDespues=aportacionServicio.findById(id).get().getDetalleAportacionList().size();
        return ResponseEntity.ok().body(aportacionAeditar);

        /**if(sizeAntes!=sizeDespues) {
            return ResponseEntity.ok().body(aportacionAeditar);
        }else{
            return ResponseEntity.notFound().build();
         }**/

    }


    @PostMapping("/aportacion/")
    public ResponseEntity<GetNuevaAportacionDto> createAportacion(@RequestBody AportacionResponseDto dto){
        if (dto.getFecha() == null || dto.getDetalles() == null || dto.getDetalles().size() == 0 || dto.getIdClase() == null)
            return ResponseEntity.badRequest().build();

        Aportacion nuevaAportacion = aportacionServicio.createAportacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aportacionDtoConverter.nuevaAportacionDto(nuevaAportacion));

    }

}
