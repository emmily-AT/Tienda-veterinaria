/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author emmilyalvarez
 */
public class ClienteDto {
    private String identificacion;
    private String nombres;
    private String direccionContacto;
    private String numeroContacto;
    private ArrayList<MascotaDto> mascotasACargo; // Ahora es ArrayList de Venta

    public ClienteDto() {
    }

    public ClienteDto(String identificacion, String nombres, String direccionContacto, String numeroContacto) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.direccionContacto = direccionContacto;
        this.numeroContacto = numeroContacto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccionContacto() {
        return direccionContacto;
    }

    public void setDireccionContacto(String direccionContacto) {
        this.direccionContacto = direccionContacto;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public ArrayList<MascotaDto> getMascotasACargo() {
        return mascotasACargo;
    }

    public void setMascotasACargo(ArrayList<MascotaDto> mascotasACargo) {
        this.mascotasACargo = mascotasACargo;
    }

    @Override
    public String toString() {
        return "Cliente {" + "Identificacion: " + identificacion + ", Nombres: " + nombres + ", Direccion de contacto: " + direccionContacto + ", Numero de contacto: " + numeroContacto + '}';
    }

}
