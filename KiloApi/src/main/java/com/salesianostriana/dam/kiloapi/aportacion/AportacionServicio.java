package com.salesianostriana.dam.kiloapi.aportacion;

import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseRepository;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.dtoDetalleAportacion.DetalleAportacionResponseDto;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AportacionServicio {

    private final AportacionRepositorio repositorio;

    private final ClaseRepository claseRepository;

    private final TipoAlimentoRepository tipoAlimentoRepository;

    public Aportacion add(Aportacion aportacion){return repositorio.save(aportacion);}

    public Optional<Aportacion> findById(Long id) {
        return repositorio.findById(id);
    }

    public List<Aportacion> findAll() {
        return repositorio.findAll();
    }

    public Aportacion edit(Aportacion aportacion) {
        return repositorio.save(aportacion);
    }

    public void delete(Aportacion aportacion) {
        repositorio.delete(aportacion);
    }

    public void deleteById(Long id) {
        repositorio.deleteById(id);
    }

    public Aportacion createAportacion (DetalleAportacionResponseDto dto){

        Aportacion aportacion = Aportacion.builder()
                .fecha(LocalDate.now()).build();
        Optional<Clase> clase = claseRepository.findById(dto.getIdClase());
        aportacion.addToClase(clase.get());

        List<DetalleAportacion> dList = new ArrayList<>();

        dto.getTipoAlimento().forEach(((aLong, aDouble) -> {
            DetalleAportacion detalleAportacion = DetalleAportacion.builder()
                    .numLinea(aportacion.getId())
                    .cantidadKilos(aDouble)
                    .tipoAlimento(tipoAlimentoRepository.findById(aLong).get())
                    .build();
            aportacion.addDetalleAportacion(detalleAportacion);
            dList.add(detalleAportacion);
        }));
        return aportacion;
    }


}
