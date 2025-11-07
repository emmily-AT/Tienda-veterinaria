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

public class VentaDto {
    private String idVenta;
    private String fechaHora;
    private double valorVenta;
    private ClienteDto cliente;
    private VendedorDto vendedor;
    private ArrayList<MascotaDto> mascotas;
    private ArrayList<Detalle> detalles; 

    public VentaDto() {
    }

    public VentaDto(String idVenta, String fechaHora, double valorVenta, ClienteDto cliente, VendedorDto vendedor) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.valorVenta = valorVenta;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.mascotas = new ArrayList<>();
        this.detalles = new ArrayList<>();
    }

    
    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public VendedorDto getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDto vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<MascotaDto> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<MascotaDto> mascotas) {
        this.mascotas = mascotas;
    }

    public ArrayList<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<Detalle> detalles) {
        this.detalles = detalles;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public String toString() {
        return "VentaDto{" + "idVenta=" + idVenta + ", fechaHora=" + fechaHora + ", valorVenta=" + valorVenta + ", cliente=" + cliente + ", vendedor=" + vendedor + ", \n\nmascotas=" + mascotas + ", \n\ndetalles=" + detalles + '}';
    }
}
