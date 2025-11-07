/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.MascotaDto;
import java.util.List;

/**
 *
 * @author emmilyalvarez
 */
public interface MascotaDao {
    public boolean almacenarMascota(MascotaDto mascota);
    public MascotaDto consultarMascota(String id);
    public List<MascotaDto> listarMascotas();
    public boolean eliminarMascota(String id); //podría ser vender sin RESOLVER
    public boolean actualizarMascota (MascotaDto mascota);
}
