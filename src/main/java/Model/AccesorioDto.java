/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author emmilyalvarez
 */
public class AccesorioDto {
    private String id;
    private String tipo;
    private String descripcion;
    private double precio;

    public AccesorioDto() {
    }

    public AccesorioDto(String id, String tipo, String descripcion, double precio) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "AccesorioDto{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
}
