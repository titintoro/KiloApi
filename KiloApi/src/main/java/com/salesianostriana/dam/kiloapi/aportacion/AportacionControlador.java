package com.salesianostriana.dam.kiloapi.aportacion;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AportacionControlador {

    private final AportacionRepositorio aportacionRepositorio;
    private final AportacionServicio aportacionServicio;



}
