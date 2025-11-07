/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.List;
import Model.VendedorDto;
/**
 *
 * @author emmilyalvarez
 */
public interface VendedorDao {
    public boolean almacenarVendedor(VendedorDto vendedor);
    public VendedorDto consultarVendedor(String identificacion);
    public List<VendedorDto> listarVendedores();
    public boolean eliminarVendedor(String identificacion);
    public boolean actualizarVendedor(VendedorDto vendedor);
}
