package com.salesianostriana.dam.kiloapi.kilosDisp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KilosDispControlador {

    private final KilosDispService service;

    @GetMapping("/kilosDisponibles/")
    public ResponseEntity<List<KilosDisp>> findAllKilosDisp(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/kilosDisponibles/{idTipoAlimento}")
    public ResponseEntity<KilosDisp> findByIdKilosDisp(@PathVariable Long idTipoAlimento){
        return ResponseEntity.of(service.findById(idTipoAlimento));
    }
}
