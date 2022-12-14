# trianafy-base

## API para tu propia biblioteca musical - Trabajo AD/PSP

---

## **Introducción**

Este es un ejercicio práctico para el manejo de APIs con **SpringBootApp**.

Se ha trabajado sobre la siguiente **documentación:**

- [Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)
- [Swagger](https://javadoc.io/doc/io.swagger.core.v3/swagger-annotations/latest/index.html)

También se ha prácticado el manejo de **PostMan** y de metodologías ágiles como **SCRUM** para el reparto de tareas a través de **Panel Notion adjuntado en el proyecto como imágenes**.

Se pueden realizar las siguientes funcionalidades:

- Busqueda de canciones,artistas,playlist
- Detallado de artistas,canciones,playlist
- Agregar canciones,artistas y playlist
- Borrar canciones,artistas y playlist
- Actualizar la información de canciones,artistas y playlist

---

## **Tecnologías utilizadas**

Para realizar este proyecto hemos utilizado:

1. [Lombok](https://projectlombok.org/)
2. [Spring](https://spring.io/guides/gs/spring-boot/) 
3. [Notion](https://cottony-tuna-d7f.notion.site/TrianaFy-fc49ab7db56a4693873faa518a80bb19) ( Aquí puede ver las historias de usuario usadas para trabajar con la metodología SCRUM )
4. [Postman](https://www.postman.com/)

### Código:

Artist List:

```Java
        @Operation(summary = "Listar todos los artistas guardadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Muestra una lista con todos los artistas guardados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado artistas",
                    content = @Content),

    })
    @GetMapping("/")
    public ResponseEntity<List<Artist>> listAllArtists() {
        return ResponseEntity.ok(artistService.findAll());
    }

```

SongDetails:

```Java
    @Operation(summary = "Muestra la información de una canción buscada por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Muestra la cancion buscada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la cancion",
                    content = @Content),

    })
    @GetMapping("/{id}")
    public ResponseEntity<Song> getOneSongInfo(@PathVariable Long id) {
        return ResponseEntity.of(songService.findById(id));
    }
```
### EndPoints

* `Listado de artistas`

  * Endpoint:
    `/artist/`

* `Artista detallado`

  * Endpoint:
    `/artist/{id}`
  * Parameters:  
    * `id`

* `Listado de canciones`

  * Endpoint:
    `/song/`

* `Canción detallada`

  * Endpoint:
    `/song/{id}`
  * Parameters:  
    * `id`

* `Listado de playlist`

  * Endpoint:
    `/list/`

* `Playlist detallada`

  * Endpoint:
    `/list/{id}`
  * Parameters:  
    * `id`
---
## **Autor**

Este proyecto ha sido realizado por:


- [David García María - GITHUB](https://github.com/davidgm26)

Estudiante de 2º Desarrollo de Aplicaciones Multiplataforma, grado
superior de formación profesional en España.


