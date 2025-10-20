package com.example.juego.model;

import java.util.List;

public class Personaje {
    private int id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private int ataque;
    private int defensa;
    private int estamina;
    private List<Integer> habilidades;

    // Getters, setters y constructor vacío


    public Personaje(){}


    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setEstamina(int estamina) {
        this.estamina = estamina;
    }

    public void setHabilidades(List<Integer> habilidades) {
        this.habilidades = habilidades;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getEstamina() {
        return estamina;
    }

    public List<Integer> getHabilidades() {
        return habilidades;
    }
}
