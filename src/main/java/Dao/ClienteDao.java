/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.List;
import Model.ClienteDto;
/**
 *
 * @author emmilyalvarez
 */
public interface ClienteDao {
    public boolean almacenarCliente(ClienteDto cliente);
    public ClienteDto consultarCliente(String identificacion);
    public List<ClienteDto> listarClientes();
    public boolean eliminarCliente(String identificacion);
    public boolean actualizarCliente(ClienteDto estudiante);
}
