package com.salesianostriana.dam.kiloapi.aportacion;

import com.salesianostriana.dam.kiloapi.aportacion.dtosAportacion.AportacionResponseDto;
import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseRepository;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacionRepositorio;
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

    private final DetalleAportacionRepositorio detallesRepo;

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

    public Aportacion createAportacion (AportacionResponseDto dto){

        Aportacion aportacion = Aportacion.builder()
                .fecha(LocalDate.now()).build();
        Optional<Clase> clase = claseRepository.findById(dto.getIdClase());
        aportacion.addToClase(clase.get());
        this.add(aportacion);

        List<DetalleAportacion> dList = new ArrayList<>();
        Aportacion aportaciondb = repositorio.save(aportacion);
        dto.getDetalles().forEach(((detalleAportacionDto) -> {
            //Hacer un if else donde comprobar si existen kilos disponibles de ese tipo de alimento
            DetalleAportacion detalleAportacion = DetalleAportacion.builder()
                    .numLinea(aportacion.getId())
                    .cantidadKilos(detalleAportacionDto.getKilosAlimento())
                    .tipoAlimento(tipoAlimentoRepository.findById(detalleAportacionDto.getIdTipoAlimento()).get())
                    .build();
            //aportacion.addDetalleAportacion(detalleAportacion);
            detalleAportacion.setNumLinea(Long.valueOf(dList.size()+1));
            detalleAportacion.setAportacion(aportaciondb);
            dList.add(detalleAportacion.addToAportacion(aportaciondb));
        }));
        aportacion.setDetalleAportacionList(dList);
        return aportacion;
    }


}
