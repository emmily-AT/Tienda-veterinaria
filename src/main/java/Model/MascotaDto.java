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
public class MascotaDto {
    private String nombre;
    private String raza;
    private int edad;
    private String tipo;
    private String id;
    private double peso;
    private String fechaIngreso;
    private String lugarOrigen;
    private char genero;
    private double precio;
    private String estado;
    private ArrayList<Consulta> consultas  = new ArrayList<>();
    private ArrayList<Dosis> vacunas = new ArrayList<>();

    public MascotaDto() {
        this.consultas = new ArrayList<>();
    }

    public MascotaDto(String nombre, String raza, int edad, String tipo, String id, double peso, String fechaIngreso, String lugarOrigen, char genero, double precio, String estado) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.tipo = tipo;
        this.id = id;
        this.peso = peso;
        this.fechaIngreso = fechaIngreso;
        this.lugarOrigen = lugarOrigen;
        this.genero = genero;
        this.precio = precio;
        this.estado = estado;
        this.consultas = new ArrayList<>();
        this.vacunas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }

    public ArrayList<Dosis> getVacunas() {
        return vacunas;
    }

    public void setVacunas(ArrayList<Dosis> vacunas) {
        this.vacunas = vacunas;
    }

    @Override
    public String toString() {
        return "Mascota {" + "nombre=" + nombre + ", raza=" + raza + ", edad=" + edad + ", tipo=" + tipo + ", id=" + id + ", peso=" + peso + ", fechaIngreso=" + fechaIngreso + ", lugarOrigen=" + lugarOrigen + ", genero=" + genero + ", precio=" + precio + ", estado=" + estado + '}';
    }

}
