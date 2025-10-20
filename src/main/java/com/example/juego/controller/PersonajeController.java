package com.example.juego.controller;

import com.example.juego.model.Personaje;
import com.example.juego.repository.DatosJuego;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    @GetMapping
    public ResponseEntity<List<Personaje>> obtenerPersonajes(
            @RequestParam(required = false) String nombre) {

        // Inicializamos para evitar error de variable no asignada
        List<Personaje> resultados = DatosJuego.PERSONAJES;

        if (nombre != null) {
            resultados = DatosJuego.PERSONAJES.stream()
                    .filter(p -> p.getNombre()
                            .toLowerCase()
                            .contains(nombre.toLowerCase()))
                    .toList();

            // Si no hay resultados: 204 No Content
            if (resultados.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }

        //  Si hay resultados (filtrados o no): 200 OK
        return ResponseEntity.ok(resultados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPorId(@PathVariable int id) {
        return DatosJuego.PERSONAJES.stream()
                .filter(p -> p.getId() == id)
                .findFirst()

                // Si se encuentra: 200 OK
                .map(ResponseEntity::ok)

                // Si no se encuentra: 404 Not Found
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<Personaje>> buscarPorNombre(
            @RequestParam String nombre) {

        List<Personaje> resultados = DatosJuego.PERSONAJES.stream()
                .filter(p -> p.getNombre() != null &&
                        p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();

        // Si no hay coincidencias: 204 No Content
        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Si se encuentran coincidencias: 200 OK
        return ResponseEntity.ok(resultados);
    }


    // GET /api/personajes?tipo=guerrero
    @GetMapping(params = "tipo")
    public ResponseEntity<List<Personaje>> filtrarPorTipo(
            @RequestParam String tipo) {

        List<Personaje> resultados = DatosJuego.PERSONAJES.stream()
                .filter(p -> p.getTipo() != null &&
                        p.getTipo().equalsIgnoreCase(tipo))
                .toList();

        // Si no hay coincidencias: 204 No Content
        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Si hay coincidencias: 200 OK
        return ResponseEntity.ok(resultados);
    }



    @PostMapping
    public ResponseEntity<Personaje> crearPersonaje(@RequestBody Personaje in) {

        // Validación mínima
        if (in.getNombre() == null || in.getNombre().isBlank()) {
            return ResponseEntity.badRequest().build(); // 400
        }

        // Generar id nuevo
        int nuevoId = DatosJuego.PERSONAJES.stream()
                .mapToInt(Personaje::getId)
                .max()
                .orElse(0) + 1;

        in.setId(nuevoId);
        DatosJuego.PERSONAJES.add(in);

        // 201 + Location
        return ResponseEntity
                .created(URI.create("/api/personajes/" + nuevoId))
                .body(in);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizarPersonaje(
            @PathVariable int id,
            @RequestBody Personaje datosActualizados) {

        // Buscar personaje por ID
        return DatosJuego.PERSONAJES.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(personaje -> {
                    // Actualizamos los campos necesarios
                    if (datosActualizados.getNombre() != null)
                        personaje.setNombre(datosActualizados.getNombre());
                    if (datosActualizados.getTipo() != null)
                        personaje.setTipo(datosActualizados.getTipo());
                    if (datosActualizados.getDescripcion() != null)
                        personaje.setDescripcion(datosActualizados.getDescripcion());
                    personaje.setAtaque(datosActualizados.getAtaque());
                    personaje.setDefensa(datosActualizados.getDefensa());
                    personaje.setEstamina(datosActualizados.getEstamina());
                    personaje.setHabilidades(datosActualizados.getHabilidades());

                    // Devolvemos el objeto actualizado
                    return ResponseEntity.ok(personaje); // 200 OK
                })
                // Si no se encuentra: 404 Not Found
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonaje(@PathVariable int id) {

        boolean eliminado = DatosJuego.PERSONAJES.removeIf(p -> p.getId() == id);

        // Si se elimina correctamente: 204 No Content
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        // Si no existe el personaje: 404 Not Found
        return ResponseEntity.notFound().build();
    }

}
