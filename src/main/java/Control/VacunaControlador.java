package Control;

import Model.VacunaDto;
import Business.VacunaNegocio;
import Tablas.TablaVacunas;
import javax.swing.JOptionPane;

public class VacunaControlador {

    VacunaNegocio vacunaNegocio = new VacunaNegocio();

    public void almacenarVacuna(String id, String tipo, String descripcion){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!tipo.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre o tipo sólo pueden contener letras");
            return;
            
        }if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
            
        }if(id.equals("")|| descripcion.equals("")|| tipo.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar una vacuna, intentelo de nuevo.");
            return;
        }else{
            VacunaDto vacuna = new VacunaDto(id, tipo, descripcion);
            if(vacunaNegocio.almacenarVacuna(vacuna)){
                operacionExitosa("Se ha almacenado la vacuna");
            }else{
                if(JOptionPane.showConfirmDialog(null, "La vacuna ya se encuentra registrada, ¿desea"
                                + " actualizar la información con los datos ingresados?", "Vacuna existente",
                        JOptionPane.YES_NO_OPTION) == 0){
                    actualizarVacuna(id, tipo, descripcion);
                }
            }
            return;
        }
    }
    
    public void consultarVacuna(String id){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        if(id.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de ingresar un id.");
            return;
        }
        VacunaDto vacuna = vacunaNegocio.consultarVacuna(id);
        if(vacuna== null){
            operacionExitosa("El número de id ingresado no coincide con ninguno de "
                    + "los id registrados");
        }else {
            operacionExitosa("Datos personales:\n\n" + vacuna.toString());
        }
    }
    
    public void eliminarVacuna(String id){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        
        if(vacunaNegocio.eliminarVacuna(id)){
            operacionExitosa("Se ha eliminado la vacuna");
        }else{
            JOptionPane.showMessageDialog(null, "Ninguna vacuna coincide con el número de id ingresado",
                    "Vacuna no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarVacuna(String id, String tipo, String descripcion){
        //Matches verificaque el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!tipo.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre o tipo sólo pueden contener letras");
            return;
            
        }if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        if(id.equals("")|| descripcion.equals("")|| tipo.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar una vacuna, intentelo de nuevo.");
            return;
        }else {
            VacunaDto vacuna = new VacunaDto(id, tipo, descripcion);
            if (vacunaNegocio.actualizarVacuna(vacuna)) {
                operacionExitosa("Se ha actualizado la vacuna");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar la vacuna\n"
                        + "Por favor verifique si la vacuna se encuentra registrado");
            }
        }
    }
    
    public void listarVacunas(){
        TablaVacunas ventana = new TablaVacunas();
        ventana.llenarTabla();
        ventana.setVisible(true);
    }
    
    public void operacionExitosa(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void datosErroneos(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
    }

}
