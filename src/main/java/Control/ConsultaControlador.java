/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Business.MascotaNegocio;
import Model.Consulta;
import Model.MascotaDto;
import javax.swing.JOptionPane;

/**
 *
 * @author emmilyalvarez
 */

public class ConsultaControlador {

    private final MascotaNegocio control = new MascotaNegocio();

    public boolean registrarConsultaEnMascota(String fecha, String idMascota, String sintomas, String tratamiento) {
        try {
            if (idMascota.isEmpty() || sintomas.isEmpty() || tratamiento.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                return false; 
            }

            MascotaDto mascota = control.consultarMascota(idMascota);

            if (mascota == null) {
                JOptionPane.showMessageDialog(null, "No se encontró una mascota con ese ID");
                return false; 
            }

            Consulta consulta = new Consulta(fecha, sintomas, tratamiento);
            mascota.getConsultas().add(consulta);

            if (control.actualizarMascota(mascota)) {
                JOptionPane.showMessageDialog(null, "Consulta registrada correctamente");
                return true; 

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar la consulta");
                return false; 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la consulta: " + e.getMessage());
            e.printStackTrace();
            return false; 
        }
    }
}
