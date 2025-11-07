/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.VentaDto;
import java.util.List;

/**
 *
 * @author emmilyalvarez
 */
public interface VentaDao {
    public boolean almacenarVenta(VentaDto mascota);
    public VentaDto consultarVenta(String id);
    public List<VentaDto> listarVentas();
}
