/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Business.MascotaNegocio;
import Business.VacunaNegocio;
import Model.Consulta;
import Model.Dosis;
import Model.MascotaDto;
import Model.VacunaDto;
import javax.swing.JOptionPane;

/**
 *
 * @author emmilyalvarez
 */

public class DosisControlador {

    private MascotaNegocio mascotaNegocio = new MascotaNegocio();
    private VacunaNegocio vacunaNegocio = new VacunaNegocio();

    public boolean registrarVacunaMascota(String fecha, String idMascota, String idVacuna, String cantidad) {
        try {
            if (idMascota.isEmpty() || idVacuna.isEmpty() || cantidad.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                return false; 
            }

            MascotaDto mascota = mascotaNegocio.consultarMascota(idMascota);

            if (mascota == null) {
                JOptionPane.showMessageDialog(null, "No se encontró una mascota con ese ID");
                return false; 
            }

            VacunaDto vacuna = vacunaNegocio.consultarVacuna(idVacuna);
            
            if (vacuna == null) {
                JOptionPane.showMessageDialog(null, "No se encontró una vacuna con ese ID");
                return false; 
            }
            
            Dosis vacunaDosis = new Dosis(fecha, Double.parseDouble(cantidad), vacuna);
            mascota.getVacunas().add(vacunaDosis);

            if (mascotaNegocio.actualizarMascota(mascota)) {
                JOptionPane.showMessageDialog(null, "Vacuna registrada correctamente");
                return true; 

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar la vacuna");
                return false; 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la consulta: " + e.getMessage());
            e.printStackTrace();
            return false; 
        }
    }
}
