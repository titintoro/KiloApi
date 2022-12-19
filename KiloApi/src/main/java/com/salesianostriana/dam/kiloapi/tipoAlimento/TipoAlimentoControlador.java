package com.salesianostriana.dam.kiloapi.tipoAlimento;

import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import com.salesianostriana.dam.kiloapi.tipoAlimento.dto.TipoAlimentoRequest;
import com.salesianostriana.dam.kiloapi.tipoAlimento.dto.TipoAlimentoResponse;
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

    @GetMapping("/")
    public ResponseEntity<List<TipoAlimento>> listAllTipoAlimento() {
        List<TipoAlimento> data = tipoAlimentoServicio.findAll();

        if (data.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimento> getOneTipoAlimentoInfo(@PathVariable Long id) {
        Optional<TipoAlimento> result = tipoAlimentoServicio.findById(id);

        if (!result.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result.get());

    }

    @PostMapping("/")
    public ResponseEntity<TipoAlimento> addOneTipoAlimento(@RequestBody String nombre) {

        if (nombre.isEmpty())
            return ResponseEntity.badRequest().build();

        TipoAlimento result = TipoAlimento.builder()
                .nombre(nombre)
                .build();
        tipoAlimentoServicio.add(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAlimento> updateOneTipoAlimento(@RequestBody TipoAlimentoRequest tipoAlimentoRequest, @PathVariable Long id) {
        Optional<TipoAlimento> tipoAlimento = tipoAlimentoServicio.findById(id);

        if (tipoAlimento.isEmpty())
            return ResponseEntity.badRequest().build();

        tipoAlimento.get().setNombre(tipoAlimentoRequest.getNombre());
        tipoAlimento.get().getKilosDisp().setCantidadDisponible(tipoAlimentoRequest.getKilosDisp());

        tipoAlimentoServicio.edit(tipoAlimento.get());


        return ResponseEntity.ok(tipoAlimento.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoAlimento(@PathVariable Long id) {
        Optional<TipoAlimento> aux = tipoAlimentoServicio.findById(id);
        TipoAlimento t = aux.get();

        if (tipoAlimentoServicio.checkCantidad(id).getTieneList().isEmpty()) {
            tipoAlimentoServicio.delete(aux.get());

        } else {
            tipoAlimentoServicio.checkCantidad(id).getTieneList()
                                                                .stream().forEach(tiene -> tiene.setTipoAlimento(null));
            tipoAlimentoServicio.delete(aux.get());
        }
        return ResponseEntity.noContent().build();
    }

}