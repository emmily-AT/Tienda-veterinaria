/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author emmilyalvarez
 */
public class VendedorDto {
    private String nombres;
    private String identificacion;
    private char genero;

    public VendedorDto() {
    }

    public VendedorDto(String nombres, String identificacion, char genero) {
        this.nombres = nombres;
        this.identificacion = identificacion;
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "VendedorDto{" + "nombres=" + nombres + ", identificacion=" + identificacion + ", genero=" + genero + '}';
    }

    
    
    
}
