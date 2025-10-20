package com.example.juego.controller;

import com.example.juego.model.Habilidad;
import com.example.juego.repository.DatosJuego;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadController {

    @GetMapping
    public ResponseEntity<List<Habilidad>> obtenerPersonajes(
            @RequestParam(required = false) String nombre) {

        //Inicializamos para evitar error de variable no asignada
        List<Habilidad> resultados = DatosJuego.HABILIDADES;

        if (nombre != null) {
            resultados = DatosJuego.HABILIDADES.stream()
                    .filter(p -> p.getNombre()
                            .toLowerCase()
                            .contains(nombre.toLowerCase()))
                    .toList();

            // Si no hay resultados: 204 No Content
            if (resultados.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }

        // Si sí hay resultados: 200 OK
        return ResponseEntity.ok(resultados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> obtenerPorId(@PathVariable int id) {
        return DatosJuego.HABILIDADES.stream()
                .filter(p -> p.getId() == id)
                .findFirst()

                // Si se encuentra, devolver 200 OK
                .map(ResponseEntity::ok)

                // Si no se encuentra, devolver 404 Not Found
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // GET /api/habilidades?orden=stamina
    @GetMapping(params = "orden")
    public ResponseEntity<List<Habilidad>> ordenarPorCampo(
            @RequestParam String orden) {

        List<Habilidad> resultados = DatosJuego.HABILIDADES;

        if (orden != null) {

            if (orden.equalsIgnoreCase("stamina")) {
                resultados = DatosJuego.HABILIDADES.stream().sorted((h1, h2) -> Integer.compare(h1.getIncrementoEstamina(), h2.getIncrementoEstamina())).toList();

            } else if (orden.equalsIgnoreCase("ataque")) {
                resultados = DatosJuego.HABILIDADES.stream().sorted((h1, h2) -> Integer.compare(h1.getIncrementoAtaque(), h2.getIncrementoAtaque())).toList();

            } else if (orden.equalsIgnoreCase("defensa")) {
                resultados = DatosJuego.HABILIDADES.stream().sorted((h1, h2) -> Integer.compare(h1.getIncrementoDefensa(), h2.getIncrementoDefensa())).toList();

            } else {

                // Si se envía un valor de orden no reconocido: 400 Bad Request
                return ResponseEntity.badRequest().build();
            }
        }

        // Si se obtienen resultados válidos: 200 OK
        return ResponseEntity.ok(resultados);
    }


    @PostMapping
    public ResponseEntity<Habilidad> crearHabilidad(@RequestBody Habilidad in) {

        // Validación mínima
        if (in.getNombre() == null || in.getNombre().isBlank()) {
            return ResponseEntity.badRequest().build(); // 400
        }

        // Generar nuevo ID
        int nuevoId = DatosJuego.HABILIDADES.stream()
                .mapToInt(Habilidad::getId)
                .max()
                .orElse(0) + 1;

        in.setId(nuevoId);
        DatosJuego.HABILIDADES.add(in);

        // 201 Created + Location
        return ResponseEntity
                .created(URI.create("/api/habilidades/" + nuevoId))
                .body(in);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Habilidad> actualizarHabilidad(
            @PathVariable int id,
            @RequestBody Habilidad datosActualizados) {

        // Buscar habilidad por ID
        return DatosJuego.HABILIDADES.stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .map(habilidad -> {
                    // Actualizamos los campos necesarios
                    if (datosActualizados.getNombre() != null)
                        habilidad.setNombre(datosActualizados.getNombre());
                    if (datosActualizados.getDescripcion() != null)
                        habilidad.setDescripcion(datosActualizados.getDescripcion());
                    habilidad.setIncrementoAtaque(datosActualizados.getIncrementoAtaque());
                    habilidad.setIncrementoDefensa(datosActualizados.getIncrementoDefensa());
                    habilidad.setIncrementoEstamina(datosActualizados.getIncrementoEstamina());

                    // Devolvemos la habilidad actualizada
                    return ResponseEntity.ok(habilidad); // 200 OK
                })
                // Si no se encuentra: 404 Not Found
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabilidad(@PathVariable int id) {

        boolean eliminada = DatosJuego.HABILIDADES.removeIf(h -> h.getId() == id);

        // Si se elimina correctamente: 204 No Content
        if (eliminada) {
            return ResponseEntity.noContent().build();
        }

        // Si no existe la habilidad: 404 Not Found
        return ResponseEntity.notFound().build();
    }

}
