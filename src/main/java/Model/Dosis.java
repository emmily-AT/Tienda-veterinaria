/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author emmilyalvarez
 */
public class Dosis {
    private String fecha;
    private double cantidad;
    private VacunaDto vacuna;

    public Dosis() {
    }

    public Dosis(String fecha, double cantidad, VacunaDto vacuna) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.vacuna = vacuna;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public VacunaDto getVacuna() {
        return vacuna;
    }

    public void setVacuna(VacunaDto vacuna) {
        this.vacuna = vacuna;
    }

    @Override
    public String toString() {
        return "Dosis{" + "fecha=" + fecha + ", cantidad=" + cantidad + vacuna + '}';
    }
}
