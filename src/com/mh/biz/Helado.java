package com.mh.biz;

public class Helado {
    private String nombre;
    private String tipo;
    private int cantidad;
    private double precio;
    private String posicion;
    
    public Helado(String posicion, String nombre, String tipo, double precio, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.posicion = posicion;
        this.cantidad= cantidad;
    }

    @Override
    public String toString() {
        return String.format("[+] Posicion = %s, Nombre = %s, Tipo = %s, Cantidad = %d, Precio = %.2f €\n",
                         posicion, nombre, tipo, cantidad, precio);
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
