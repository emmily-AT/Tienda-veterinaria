package Control;

import Model.ClienteDto;
import Business.ClienteNegocio;
import Model.MascotaDto;
import Tablas.TablaClientes;
import javax.swing.JOptionPane;

public class ClienteControlador {

    ClienteNegocio clienteNegocio = new ClienteNegocio();

    public void almacenarCliente(String identificacion, String nombre, String direccion, String numero){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!nombre.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre sólo pueden contener letras");
            return;
            
        }if(!identificacion.matches("[0-9]*") || !numero.matches("[0-9]*")){
            datosErroneos("El documento y el número debe ser un número sin puntos ni comas");
            return;
        }

        if(nombre.equals("") || identificacion.equals("")|| direccion.equals("")|| numero.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar un estudiante, intentelo de nuevo.");
            return;
        }else{
            ClienteDto cliente = new ClienteDto(identificacion, nombre, direccion, numero);
            if(clienteNegocio.almacenarCliente(cliente)==true){
                operacionExitosa("Se ha almacenado el cliente");
            }else{
                if(JOptionPane.showConfirmDialog(null, "La persona ya se encuentra registrada, ¿desea"
                                + " actualizar la información con los datos ingresados?", "Estudiante existente",
                        JOptionPane.YES_NO_OPTION) == 0){
                    actualizarCliente(identificacion, nombre, direccion, numero);
                }
            }
            return;
        }
    }
    
    public void consultarCliente(String documento){
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
        ClienteDto cliente = clienteNegocio.consultarCliente(documento);
        if(cliente == null){
            operacionExitosa("El número de documento ingresado no coincide con ninguno de "
                    + "los documentos registrados");
        }else {
            String mascotasACargo = "";
            for(MascotaDto mascota: cliente.getMascotasACargo()){
                mascotasACargo += "Nombre: " + mascota.getNombre() + ", Tipo: " + mascota.getTipo() + ", Id: " + mascota.getId() + ", Precio: " + mascota.getPrecio() + "; ";
            }  
            
            operacionExitosa("Datos personales:\n" + cliente.toString() + " \n\nMascotas a cargo: \n" + "Mascotas {"+mascotasACargo + "}");
        }
    }
    
    public void eliminarCliente(String documento){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!documento.matches("[0-9]*")){
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }
        
        if(clienteNegocio.eliminarCliente(documento)){
            operacionExitosa("Se ha eliminado el cliente");
        }else{
            JOptionPane.showMessageDialog(null, "Ningún cliente coincide con el número de documento ingresado",
                    "Estudiante no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarCliente(String identificacion, String nombre, String direccion, String numero){
        //Matches verificaque el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!nombre.matches("[a-zA-Z ]*"))){
            datosErroneos("El nombre y el apellido sólo pueden contener letras");
            return;

        }if(!identificacion.matches("[0-9]*") || !numero.matches("[0-9]*")){
            datosErroneos("El documento y el número debe ser un número sin puntos ni comas");
            return;
        }
        if(nombre.equals("") || identificacion.equals("")|| direccion.equals("")|| numero.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder actualizar un estudiante, intentelo de nuevo.");
            return;
        }else {
            ClienteDto cliente = new ClienteDto(identificacion, nombre, direccion, numero);
            if (clienteNegocio.actualizarCliente(cliente)) {
                operacionExitosa("Se ha actualizado el cliente");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar el cliente\n"
                        + "Por favor verifique si el estudiante se encuentra registrado");
            }
        }
    }
    
    public void listarClientes(){
        TablaClientes ventana = new TablaClientes();
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
