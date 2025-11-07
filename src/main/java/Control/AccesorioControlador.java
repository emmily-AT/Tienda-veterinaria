package Control;

import Model.AccesorioDto;
import Business.AccesorioNegocio;
import Tablas.TablaAccesorios;
import javax.swing.JOptionPane;

public class AccesorioControlador {

    AccesorioNegocio accesorioNegocio = new AccesorioNegocio();

    public void almacenarAccesorio(String id, String tipo, String descripcion, String precio){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!tipo.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre sólo pueden contener letras");
            return;
            
        }if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }if(!precio.matches("[0-9]*")){
            datosErroneos("El precio no debe contener letras ni puntos");
            return;
        }
        if(id.equals("")|| descripcion.equals("")|| tipo.equals("") || precio.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar un accesorio, intentelo de nuevo.");
            return;
        }else{
            AccesorioDto accesorio = new AccesorioDto(id, tipo, descripcion, Double.parseDouble(precio));
            if(accesorioNegocio.almacenarAccesorio(accesorio)==true){
                operacionExitosa("Se ha almacenado el accesorio");
            }else{
                if(JOptionPane.showConfirmDialog(null, "La persona ya se encuentra registrada, ¿desea"
                                + " actualizar la información con los datos ingresados?", "Accesorio existente",
                        JOptionPane.YES_NO_OPTION) == 0){
                    actualizarAccesorio(id, tipo, descripcion, precio);
                }
            }
            return;
        }
    }
    
    public void consultarAccesorio(String documento){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!documento.matches("[0-9]*")){
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }
        if(documento.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de ingresar un documento.");
            return;
        }
        AccesorioDto accesorio = accesorioNegocio.consultarAccesorio(documento);
        if(accesorio== null){
            operacionExitosa("El número de documento ingresado no coincide con ninguno de "
                    + "los documentos registrados");
        }else {
            operacionExitosa("Datos personales:\n\n" + accesorio.toString());
        }
    }
    
    public void eliminarAccesorio(String documento){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!documento.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        
        if(accesorioNegocio.eliminarAccesorio(documento)){
            operacionExitosa("Se ha eliminado el accesorio");
        }else{
            JOptionPane.showMessageDialog(null, "Ningún accesorio coincide con el número de id ingresado",
                    "Accesorio no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarAccesorio(String id, String tipo, String descripcion, String precio){
        //Matches verificaque el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!tipo.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre sólo pueden contener letras");
            return;
            
        }if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }if(!precio.matches("[0-9.]*")){
            datosErroneos("El precio no debe contener letras");
            return;
        }
        if(id.equals("")|| descripcion.equals("")|| tipo.equals("") || precio.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar un accesorio, intentelo de nuevo.");
            return;
        }else {
            AccesorioDto accesorio = new AccesorioDto(id, tipo, descripcion, Double.parseDouble(precio));
            if (accesorioNegocio.actualizarAccesorio(accesorio)) {
                operacionExitosa("Se ha actualizado el accesorio");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar el accesorio\n"
                        + "Por favor verifique si el accesorio se encuentra registrado");
            }
        }
    }
    
    public void listarAccesorios(){
        TablaAccesorios ventana = new TablaAccesorios();
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
