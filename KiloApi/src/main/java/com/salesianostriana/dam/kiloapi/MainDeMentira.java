package com.salesianostriana.dam.kiloapi;

import com.salesianostriana.dam.kiloapi.aportacion.Aportacion;
import com.salesianostriana.dam.kiloapi.aportacion.AportacionServicio;
import com.salesianostriana.dam.kiloapi.caja.Caja;
import com.salesianostriana.dam.kiloapi.caja.CajaServicio;
import com.salesianostriana.dam.kiloapi.clase.Clase;
import com.salesianostriana.dam.kiloapi.clase.ClaseService;
import com.salesianostriana.dam.kiloapi.destinatario.Destinatario;
import com.salesianostriana.dam.kiloapi.destinatario.DestinatarioServicio;
import com.salesianostriana.dam.kiloapi.detalleAportacion.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDisp;
import com.salesianostriana.dam.kiloapi.kilosDisp.KilosDispService;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimento;
import com.salesianostriana.dam.kiloapi.tipoAlimento.TipoAlimentoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MainDeMentira{

    private final ClaseService claseService;

    private final CajaServicio cajaServicio;

    private final AportacionServicio aportacionServicio;

    private final DestinatarioServicio destinatarioServicio;


    private final KilosDispService kilosDispService;

    private final TipoAlimentoServicio tipoAlimentoServicio;

    @PostConstruct
    public void run() {

        Caja caja = Caja.builder()
                .numCaja(201)
                .qr("hhhhhhh")
                .kilosTotales(0)
                .build();

        Caja caja3 = Caja.builder()
                .numCaja(204)
                .qr("mmmmm")
                .kilosTotales(0)
                .build();

        Caja caja4 = Caja.builder()
                .numCaja(202)
                .qr("iiiiii")
                .kilosTotales(0)
                .build();

        Caja caja5 = Caja.builder()
                .numCaja(203)
                .qr("lllllll")
                .kilosTotales(0)
                .build();

        cajaServicio.add(caja);
        cajaServicio.add(caja3);
        cajaServicio.add(caja4);
        cajaServicio.add(caja5);


        Clase c = Clase.builder()
                .nombre("1 DAM")
                .tutor("Mr Bean")
                .build();
        Clase c1 = Clase.builder()
                .nombre("2 DAM")
                .tutor("The Boss")
                .build();
        Clase c2 = Clase.builder()
                .nombre("1 Telecomunicaciones")
                .tutor("Antonio Torres")
                .build();
        Clase c3 = Clase.builder()
                .nombre("1 AFY")
                .tutor("Juan")
                .build();
        Clase c4 = Clase.builder()
                .nombre("2 AYF")
                .tutor("Irene")
                .build();
        Clase c5 = Clase.builder()
                .nombre("2 Telecomunicaciones")
                .tutor("Jesus Casanova")
                .build();

        claseService.add(c);
        claseService.add(c1);
        claseService.add(c2);
        claseService.add(c3);
        claseService.add(c4);
        claseService.add(c5);

        TipoAlimento tp = TipoAlimento.builder()
                .nombre("Macarrones")
                .build();

        TipoAlimento tp1 = TipoAlimento.builder()
                .nombre("Garbanzos")
                .build();

        TipoAlimento tp2 = TipoAlimento.builder()
                .nombre("Patatas")
                .build();

        TipoAlimento tp3 = TipoAlimento.builder()
                .nombre("Lentejas")
                .build();

        KilosDisp k1 = KilosDisp.builder()
                .cantidadDisponible(8)
                .build();
        KilosDisp k2 = KilosDisp.builder()
                .cantidadDisponible(5)
                .build();
        KilosDisp k3 = KilosDisp.builder()
                .cantidadDisponible(1.2)
                .build();
        KilosDisp k4 = KilosDisp.builder()
                .cantidadDisponible(8.5)
                .build();

        tp1.addKiloToTipo(k1);
        tp1.addKiloToTipo(k2);
        tp1.addKiloToTipo(k3);
        tp1.addKiloToTipo(k4);

        Aportacion a1 = Aportacion.builder()
                .fecha(LocalDate.now())
                .build();

        DetalleAportacion dt = DetalleAportacion.builder()
                .cantidad(5.2)
                .tipoAlimento(tp)
                .aportacion(a1)
                .build();

        tipoAlimentoServicio.add(tp);
        tipoAlimentoServicio.add(tp1);
        tipoAlimentoServicio.add(tp2);
        tipoAlimentoServicio.add(tp3);

        Destinatario d = Destinatario.builder()
                .nombre("Valent??n")
                .direccion("Calle Condes de Bustillo")
                .personaContacto("Antonio")
                .telefono("654686962")
                .build();

        Destinatario d1 = Destinatario.builder()
                .nombre("Jorge")
                .direccion("Maria Auxiliadora")
                .personaContacto("Mario")
                .telefono("688524206")
                .build();

        Destinatario d2 = Destinatario.builder()
                .nombre("David")
                .direccion("Calle San Juan Bosco")
                .personaContacto("Daniel")
                .telefono("659623604")
                .build();



        destinatarioServicio.add(d);
        destinatarioServicio.add(d1);
        destinatarioServicio.add(d2);

    }}


