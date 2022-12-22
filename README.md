# KiloAPI

## API para la gestión de la campaña del Kilo de los Salesianos de San Pedro - Trabajo AD/PSP

---

## **Introducción**

Este es un ejercicio práctico para el manejo de APIs con **SpringBootApp**.

Se ha trabajado sobre la siguiente **documentación:**

- [Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)
- [Swagger](https://javadoc.io/doc/io.swagger.core.v3/swagger-annotations/latest/index.html)
- [Hibernate](https://hibernate.org/orm/documentation/6.1/)
- [Spring](https://docs.spring.io/spring-framework/docs/current/javadoc-api/)

También se ha prácticado el manejo de **PostMan**

Se pueden realizar las siguientes funcionalidades:

- Crear clases,aportaciones,alimento,cajas y destinatarios
- Borrar los comentados anteriormente
- Modificar los datos 
- Buscar uno en específico
---

## **Tecnologías utilizadas**

Para realizar este proyecto hemos utilizado:

1. [Lombok](https://projectlombok.org/)
2. [Spring](https://spring.io/guides/gs/spring-boot/) 
3. [Postman](https://www.postman.com/)
4. [Github](https://github.com/)

### Código:

Crear Caja:

```Java
    @PostMapping("/caja/{id}/tipo/{idTipoAlim}/kg/{cantidad}")
    public ResponseEntity<PostCajaAlimentoResponse> addTipoAlimToCaja(
            @PathVariable Long id , @PathVariable Long idTipoAlim, @PathVariable double cantidad) {

        if (id!=null || idTipoAlim != null || cantidad >0){
            Optional<Caja> c = cajaServicio.addAlimToCaja(id,idTipoAlim,cantidad);

            return (c.map(caja -> ResponseEntity.status(HttpStatus.CREATED).body(cajaDtoConverter.toPostCajaAlimentoResponse(caja))).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
```

Modificar kgs de un alimento en una caja:

```Java
  public Optional<Caja> updateKgsOfTipoAlimentoFromCaja(Long idCaja, Long idTipoAlim, double cantidad) {

        Optional<Caja> caja = findById(idCaja);

        double kilosDisponibles = kilosDispRepo.getKilosDispOfAlimById(idTipoAlim);

        Optional<TipoAlimento> tipoAlimento = tipoAlimentoServicio.findById(idTipoAlim);


        if (caja.isPresent() && tipoAlimento.isPresent() && cantidad<=kilosDisponibles){

            Tiene tiene = new Tiene();

            for(Tiene t : caja.get().getTieneList()) {
                if (t.getTipoAlimento().getId().equals(idTipoAlim)) tiene = t;
            }

            tipoAlimento.get().getKilosDisp().setCantidadDisponible(kilosDisponibles + tiene.getCantidadKgs());

            caja.get().setKilosTotales(caja.get().getKilosTotales()+cantidad-tiene.getCantidadKgs());

            tiene.setCantidadKgs(cantidad);

            tipoAlimentoServicio.edit(tipoAlimento.get());

            tieneRepository.save(tiene);

            cajaRepo.save(caja.get());

            return caja;
        }
        return Optional.empty();


    }
```
### EndPoints

* `Caja`

  * Endpoint `Get`:
    `/caja/`
    * Muestra todas las cajas

  * Endpoint `Get`:
    `/caja/{id}`
    * Muestra la información de una caja

  * Endpoint `Get`:
    `/caja/{id}/tipo/{idTipoAlim}/kg/{cantidad}/`
    * Editar kilos de un tipo de alimento de una caja
  
  * Endpoint `Post`: 
    `/caja/`
    * Crea una caja 

  * Endpoint `Post`:
    `/caja/{id}/tipo/{idTipoAlim}/kg/{cantidad}`
    * Añade una cantidad de un tipo de alimento a una caja
  
  * Endpoint `Post`:
    `/caja/{idCaja}/destinatario/{idDestinatario}`
    * Añade un destinatario a una caja

  * Endpoint `Put`:
    `/caja/{id}`
    * Editar información de una caja

  * Endpoint `Put`:
    `/caja/{id}/tipo/{idTipoAlim}/kg/{cantidad}/`
    * Editar kilos de un tipo de alimento de una caja

  * Endpoint `Delete`:
    `/caja/{id}`
    * Borra una caja
    
  * Endpoint `Delete`:
    `/caja/{id}/tipo/{idTipoAlim}`
    * Borra un tipo de alimento de una caja    
  
* `Clase`  

  * Endpoint `Get`:
    `/clase/`
    * Muestra todas las clases

  * Endpoint `Get`:
    `/clase/{id}`
    * Muestra la información de una clase
 
  * Endpoint `Post`: 
    `/clase/`
    * Crea una clase
  
  * Endpoint `Put`:
    `/clase/{id}`
    * Edita la información de una clase
  
  * Endpoint `Delete`:
    `/clase/{id}`
    * Borra una clase

* `Destinatario`  

  * Endpoint `Get`:
    `/destinatario/`
    * Muestra todos los destinatarios

  * Endpoint `Get`:
    `/destinatario/{id}`
    * Devuelve los datos de un destinatario.

  * Endpoint `Get`:
    `/destinatario/{id}/detalle`
    * Devuelve los datos de un destinatario,las cajas enviadas para él y el contenido de estas.
 
  * Endpoint `Post`: 
    `/destinatario/`
    * Crea un destinatario
  
  * Endpoint `Put`:
    `/destinatario/{id}`
    * Edita la información de un destinatario
  
  * Endpoint `Delete`:
    `/destinatario/{id}`
    * Borra un destinatario

* `Kilos Disponibles`  

  * Endpoint `Get`:
    `/kilosDisponibles/`
    * Muestra todos los kilos disponibles de cada tipo de alimento

  * Endpoint `Get`:
    `/kilosDisponibles/{idTipoAlimento}`
    * Devuelve los kilos disponibles de un tipo de alimento en específico.

* `Destinatario`  

  * Endpoint `Get`:
    `/destinatario/`
    * Muestra todos los destinatarios

  * Endpoint `Get`:
    `/destinatario/{id}`
    * Devuelve los datos de un destinatario.

  * Endpoint `Get`:
    `/destinatario/{id}/detalle`
    * Devuelve los datos de un destinatario,las cajas enviadas para él y el contenido de estas.
 
  * Endpoint `Post`: 
    `/destinatario/`
    * Crea un destinatario
  
  * Endpoint `Put`:
    `/destinatario/{id}`
    * Edita la información de un destinatario
  
  * Endpoint `Delete`:
    `/destinatario/{id}`
    * Borra un destinatario

* `Aportacion`

  * Endpoint `Get`:
    `/aportacion/`
    * Muestra todas las aportaciones

  * Endpoint `Get`:
    `/aportacion/{id}`
    * Devuelve los datos de una aportación.

  * Endpoint `Get`:
    `/aportacion/clase/{idClase}`
    * Devuelve los datos de las aportaciones de una clase
 
  * Endpoint `Post`: 
    `/destinatario/`
    * Crea una aportación
  
  * Endpoint `Delete`:
    `//aportacion/{id}/linea/{num}`
    * Borra una línea de la aportación 

  * Endpoint `Delete`:
    `/aportacion/{id}`
    * Borra una aportacion

* `Tipo de Alimento`

  * Endpoint `Get`:
    `/tipoAlimento/`
    * Muestra todos los alimentos disponibles.

  * Endpoint `Get`:
    `/tipoAlimento/{id}`
    * Devuelve los datos de un tipo de alimento específico.

  * Endpoint `Post`: 
    `/tipoAlimentoio/`
    * Crea un nuevo tipo alimento
  
  * Endpoint `Put`:
    `/tipoAlimento/`
    * Actualiza la información de un tipo de alimento.
    
  * Endpoint `Delete`:
    `/tipoAlimento/{id}`
    * Borra un tipo de alimento.
---
## **Autores**

Este proyecto ha sido realizado por:


- [Valentín Tola Rodríguez - GITHUB](https://github.com/titintoro)
- [Alejandro Fernández Gómez-Caminero - GITHUB](https://github.com/ale061202)
- [Jorge Miguel Tenorio Rodríguez  - GITHUB](https://github.com/jorgetenorio96)
- [David García María - GITHUB](https://github.com/davidgm26)

Estudiantes de 2º Desarrollo de Aplicaciones Multiplataforma, grado
superior de formación profesional en España.


