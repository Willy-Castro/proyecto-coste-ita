package com.resta.web.model;

public class Insumo {// en la clase insumo se guarda los tributos usuario, contraseña y dni
    private String nombre;
    private String categoria;
    private int cantidad;

    public Insumo() {
    }

    public Insumo(String nombre, String categoria, int cantidad) {// guarda los datos del insumo en las variables
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}