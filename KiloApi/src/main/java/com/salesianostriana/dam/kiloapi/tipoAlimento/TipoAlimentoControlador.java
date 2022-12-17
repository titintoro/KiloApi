package com.salesianostriana.dam.kiloapi.tipoAlimento;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipoAlimento")
@RequiredArgsConstructor
@Tag(name = "TipoAlimento", description = "Controlador encargado de gestionar todo lo relacionado con los distintos tipos de alimentos donados")
public class TipoAlimentoControlador {

    private final TipoAlimentoServicio tipoAlimentoServicio;


    @GetMapping("/")
    public ResponseEntity<List<TipoAlimento>> listAllTipoAlimento(){
    List<TipoAlimento> data = tipoAlimentoServicio.findAll();

    if(data.isEmpty())
        return ResponseEntity.notFound().build();
    return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimento> getOneTipoAlimentoInfo(@PathVariable Long id){
        TipoAlimento result = tipoAlimentoServicio.findById(id).orElse(null);

        if (result==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);

    }




}
