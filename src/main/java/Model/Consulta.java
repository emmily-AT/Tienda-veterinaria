/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author emmilyalvarez
 */
public class Consulta {
    private String fecha;
    private String sintomas;
    private String tratamiento;

    public Consulta() {
    }

    public Consulta(String fecha, String sintomas, String tratamiento) {
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
    }

    public String getFechaHora() {
        return fecha;
    }

    public void setFechaHora(String fechaHora) {
        this.fecha = fechaHora;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Override
    public String toString() {
        return "{"+ "fecha=" + fecha + ", sintomas=" + sintomas + ", tratamiento=" + tratamiento + '}';
    }
}
