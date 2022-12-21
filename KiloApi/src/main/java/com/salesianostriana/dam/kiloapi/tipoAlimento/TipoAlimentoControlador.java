package com.salesianostriana.dam.kiloapi.tipoAlimento;

import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
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

    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimentoResponse> getOneTipoAlimentoInfo(@PathVariable Long id) {

        TipoAlimentoResponse result = tipoAlimentoServicio.getOneTipo(id);

        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }

    @PostMapping("/")
    public ResponseEntity<TipoAlimentoResponse> addOneTipoAlimento(@RequestBody TipoAlimento tipoAlimento) {

        if (tipoAlimento.getNombre().isEmpty())
            return ResponseEntity.badRequest().build();

        tipoAlimentoServicio.add(tipoAlimento);

        TipoAlimentoResponse t = TipoAlimentoResponse.convertTipoAlimentoToResponse(tipoAlimento);

        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

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