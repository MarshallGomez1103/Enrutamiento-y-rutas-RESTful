package com.example.juego.repository;

import com.example.juego.model.Habilidad;
import com.example.juego.model.Personaje;

import java.util.ArrayList; //Se agrega para tener la mutabilidad y poder usar add() en el metodo post
import java.util.List;
import java.util.Arrays;

public class DatosJuego {

    public static final List<Habilidad> HABILIDADES = new ArrayList<>(Arrays.asList(
            new Habilidad() {{
                setId(1);
                setNombre("Espadazo");
                setDescripcion("Un ataque poderoso con la espada.");
                setIncrementoAtaque(10);
                setIncrementoDefensa(0);
                setIncrementoEstamina(-5);
            }},
            new Habilidad() {{
                setId(2);
                setNombre("Escudo de Hierro");
                setDescripcion("Aumenta la defensa del guerrero.");
                setIncrementoAtaque(0);
                setIncrementoDefensa(15);
                setIncrementoEstamina(-3);
            }},
            new Habilidad() {{
                setId(3);
                setNombre("Señal de Igni");
                setDescripcion("Una ráfaga de fuego mágico que inflige daño a los enemigos cercanos.");
                setIncrementoAtaque(12);
                setIncrementoDefensa(0);
                setIncrementoEstamina(-4);
            }},
            new Habilidad() {{
                setId(4);
                setNombre("Reflejos Cibernéticos");
                setDescripcion("Aumenta temporalmente la velocidad de reacción y la capacidad ofensiva del usuario.");
                setIncrementoAtaque(15);
                setIncrementoDefensa(5);
                setIncrementoEstamina(-6);
            }}

    ));

    public static final List<Personaje> PERSONAJES = new ArrayList<>(Arrays.asList(
            new Personaje() {{
                setId(1);
                setNombre("Gagh-Ar");
                setTipo("guerrero");
                setDescripcion("Un valiente luchador con gran fuerza física.");
                setAtaque(80);
                setDefensa(70);
                setEstamina(60);
                setHabilidades(Arrays.asList(1, 2));
            }},
            new Personaje() {{
                setId(2);
                setNombre("Elyra");
                setTipo("maga");
                setDescripcion("Hechicera con gran dominio de la energía mágica.");
                setAtaque(65);
                setDefensa(40);
                setEstamina(90);
                setHabilidades(Arrays.asList(2));
            }},

            new Personaje() {{
                setId(3);
                setNombre("Geralt");
                setTipo("brujo");
                setDescripcion("Un cazador de monstruos experto.");
                setAtaque(85);
                setDefensa(65);
                setEstamina(75);
                setHabilidades(Arrays.asList(3));
            }},
            new Personaje() {{
                setId(4);
                setNombre("V");
                setTipo("mercenario");
                setDescripcion("Un habilidoso combatiente urbano.");
                setAtaque(90);
                setDefensa(55);
                setEstamina(80);
                setHabilidades(Arrays.asList(4));
            }}
    ));
}
