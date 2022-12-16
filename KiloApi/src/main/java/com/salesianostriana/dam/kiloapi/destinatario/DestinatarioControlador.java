package com.salesianostriana.dam.kiloapi.destinatario;

import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
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

    @GetMapping("/destinatario/")
    public ResponseEntity<List<Destinatario>> findAllDestinatarios(){
        return ResponseEntity.ok(servicio.findAll());
    }

    @GetMapping("/destinatario/{id}")
    public ResponseEntity<Destinatario> findByIdDestinatarios(@PathVariable Long id){
        return ResponseEntity.of(servicio.findById(id));
    }

    @GetMapping("destinatario/{id}/detalle")
    public ResponseEntity<Destinatario> findByIdDestinatarioDetalle(@PathVariable Long id){
        return ResponseEntity.of(servicio.findById(id));
    }

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
