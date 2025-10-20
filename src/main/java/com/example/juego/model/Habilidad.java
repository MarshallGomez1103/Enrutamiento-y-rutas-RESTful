package com.example.juego.model;

public class Habilidad {
    private int id;
    private String nombre;
    private String descripcion;
    private int incrementoAtaque;
    private int incrementoDefensa;
    private int incrementoEstamina;

    // Getters, setters y constructor vacío


    public Habilidad(){}

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIncrementoAtaque(int incrementoAtaque) {
        this.incrementoAtaque = incrementoAtaque;
    }

    public void setIncrementoDefensa(int incrementoDefensa) {
        this.incrementoDefensa = incrementoDefensa;
    }

    public void setIncrementoEstamina(int incrementoEstamina) {
        this.incrementoEstamina = incrementoEstamina;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIncrementoAtaque() {
        return incrementoAtaque;
    }

    public int getIncrementoDefensa() {
        return incrementoDefensa;
    }

    public int getIncrementoEstamina() {
        return incrementoEstamina;
    }
}
