/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author emmilyalvarez
 */
public class Detalle {
    private AccesorioDto accesorio;
    private int cantidad;

    public Detalle() {
    }

    public Detalle(AccesorioDto accesorio, int cantidad) {
        this.accesorio = accesorio;
        this.cantidad = cantidad;
    }

    public AccesorioDto getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(AccesorioDto accesorio) {
        this.accesorio = accesorio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }  

    @Override
    public String toString() {
        return "Detalle{" + "accesorio=" + accesorio + ", cantidad=" + cantidad + '}';
    }
}

