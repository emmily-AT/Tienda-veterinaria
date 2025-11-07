/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Control;

import Model.VendedorDto;
import Business.VendedorNegocio;
import Tablas.TablaVendedores;
import javax.swing.JOptionPane;

/**
 *
 * @author emmilyalvarez
 */

public class VendedorControlador {

    VendedorNegocio vendedorNegocio = new VendedorNegocio();

    public void almacenarVendedor(String nombres, String identificacion, String genero){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!nombres.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre sólo pueden contener letras");
            return;
            
        }if(!identificacion.matches("[0-9]*")){
            datosErroneos("El documento y el número debe ser un número sin puntos ni comas");
            return;
        }if(!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))){
            datosErroneos("Por favor ingrese en el género \"F\" para femenino o \"M\" "
                    + "para masculino (sin comillas)");
            return;
        } if(nombres.equals("") || identificacion.equals("")|| genero.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar un estudiante, intentelo de nuevo.");
            return;
        }else{
            char generoChar = genero.charAt(0);
            VendedorDto vendedor = new VendedorDto(nombres, identificacion, generoChar);
            if(vendedorNegocio.almacenarVendedor(vendedor)==true){
                operacionExitosa("Se ha almacenado el vendedor");
            }else{
                if(JOptionPane.showConfirmDialog(null, "La persona ya se encuentra registrada, ¿desea"
                                + " actualizar la información con los datos ingresados?", "Vendedor existente",
                        JOptionPane.YES_NO_OPTION) == 0){
                    actualizarVendedor(nombres, identificacion, genero);
                }
            }
            return;
        }
    }
    
    public void consultarVendedor(String documento){
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
        VendedorDto vendedor = vendedorNegocio.consultarVendedor(documento);
        if(vendedor == null){
            operacionExitosa("El número de documento ingresado no coincide con ninguno de "
                    + "los documentos registrados");
        }else {
            operacionExitosa("Datos personales:\n\n" + vendedor.toString());
        }
    }
    
    public void eliminarVendedor(String documento){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!documento.matches("[0-9]*")){
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }
        
        if(vendedorNegocio.eliminarVendedor(documento)){
            operacionExitosa("Se ha eliminado el vendedor");
        }else{
            JOptionPane.showMessageDialog(null, "Ningún vendedor coincide con el número de documento ingresado",
                    "Vendedor no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarVendedor(String nombres, String identificacion, String genero){
        //Matches verificaque el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!nombres.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre sólo pueden contener letras");
            return;
            
        }if(!identificacion.matches("[0-9]*")){
            datosErroneos("El documento y el número debe ser un número sin puntos ni comas");
            return;
        }if(!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))){
            datosErroneos("Por favor ingrese en el género \"F\" para femenino o \"M\" "
                    + "para masculino (sin comillas)");
            return;
        } if(nombres.equals("") || identificacion.equals("")|| genero.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar un estudiante, intentelo de nuevo.");
            return;
        }else {
            char generoChar = genero.charAt(0);
            VendedorDto vendedor = new VendedorDto(nombres, identificacion, generoChar);
            if (vendedorNegocio.actualizarVendedor(vendedor)) {
                operacionExitosa("Se ha actualizado el vendedor");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar el estudiante\n"
                        + "Por favor verifique si el estudiante se encuentra registrado");
            }
        }
    }
    
    public void listarVendedores(){
        TablaVendedores ventana = new TablaVendedores();
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
