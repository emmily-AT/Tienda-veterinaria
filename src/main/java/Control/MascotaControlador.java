package Control;

import Model.MascotaDto;
import Business.MascotaNegocio;
import Tablas.TablaMascotas;
import javax.swing.JOptionPane;

public class MascotaControlador {

    MascotaNegocio mascotaNegocio = new MascotaNegocio();

    public void almacenarMascota(String nombre, String raza, String edad, String tipo, String id, String peso, String fechaIngreso, String lugarOrigen, String genero, String precio, String estado){ //fechas
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!nombre.matches("[a-zA-Z ]*")) || (!raza.matches("[a-zA-Z ]*")) || (!tipo.matches("[a-zA-Z ]*")) || (!lugarOrigen.matches("[a-zA-Z ]*")) || (!estado.matches("[a-zA-Z ]*"))){
            datosErroneos("Los campos nombre, raza, tipo, lugar de origen y estado sólo pueden contener letras");
            return;
        }if(!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))){
            datosErroneos("Por favor ingrese en el género \"F\" para femenino o \"M\" "
                    + "para masculino (sin comillas)");
            return;
        }if((!id.matches("[0-9]*")) || (!edad.matches("[0-9]*"))){
            datosErroneos("El id y la edad deben ser un número sin puntos ni comas");
            return;
        }if((!peso.matches("[0-9.]*")) || (!precio.matches("[0-9.]*"))){//revisar lo de permitir puntos
            datosErroneos("El peso y el precio deben ser un número");
            return;
        }if(!(estado.equalsIgnoreCase("ADOPTADO") || estado.equalsIgnoreCase("SIN ADOPTAR"))){
            datosErroneos("Estado(Adoptado/Sin adoptar)");
            return;
        }    

        if(nombre.equals("") || raza.equals("")|| edad.equals("")|| tipo.equals("") || id.equals("") || peso.equals("")|| fechaIngreso.equals("")|| lugarOrigen.equals("") || genero.equals("") || precio.equals("")|| estado.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar una mascota, intentelo de nuevo.");
            return;
        }else{
            char generoChar = genero.charAt(0);
            MascotaDto mascota = new MascotaDto(nombre, raza, Integer.parseInt(edad), tipo, id, Double.parseDouble(peso), fechaIngreso, lugarOrigen, generoChar, Double.parseDouble(precio), estado);
            if(mascotaNegocio.almacenarMascota(mascota)==true){
                operacionExitosa("Se ha almacenado la mascota");
            }else{
                if(JOptionPane.showConfirmDialog(null, "La mascota ya se encuentra registrada, ¿desea"
                                + " actualizar la información con los datos ingresados?", "Mascota existente", //En donde se encuentra que la persona ya esta registrada 
                        JOptionPane.YES_NO_OPTION) == 0){
                    actualizarMascota(nombre, raza, edad, tipo, id, peso, fechaIngreso, lugarOrigen, genero, precio, estado);
                }
            }
            return;
        }
    }
    
    public void consultarMascota(String id){
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
        MascotaDto mascota = mascotaNegocio.consultarMascota(id);
        if(mascota == null){
            operacionExitosa("El número de id ingresado no coincide con ninguno de "
                    + "los ids registrados");
        }else {
            operacionExitosa("Datos personales:\n\n" + mascota.toString() + "\n\nConsultas: " + mascota.getConsultas() + "\n\nVacunas: " + mascota.getVacunas());
        }
    }
    
    public void eliminarMascota(String id){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        
        if(mascotaNegocio.eliminarMascota(id)){
            operacionExitosa("Se ha eliminado la mascota");
        }else{
            JOptionPane.showMessageDialog(null, "Ningún mascota coincide con el número de id ingresado",
                    "Estudiante no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarMascota(String nombre, String raza, String edad, String tipo, String id, String peso, String fechaIngreso, String lugarOrigen, String genero, String precio, String estado){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if((!nombre.matches("[a-zA-Z ]*")) || (!raza.matches("[a-zA-Z ]*")) || (!tipo.matches("[a-zA-Z ]*")) || (!lugarOrigen.matches("[a-zA-Z ]*")) || (!estado.matches("[a-zA-Z ]*"))){
            datosErroneos("Los campos nombre, raza, tipo, lugar de origen y estado sólo pueden contener letras");
            return;
        }if(!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))){
            datosErroneos("Por favor ingrese en el género \"F\" para femenino o \"M\" "
                    + "para masculino (sin comillas)");
            return;
        }if((!id.matches("[0-9]*")) || (!edad.matches("[0-9]*"))){
            datosErroneos("El id y la edad deben ser un número sin puntos ni comas");
            return;
        }if((!peso.matches("[0-9.]*")) || (!precio.matches("[0-9.]*"))){//revisar lo de permitir puntos
            datosErroneos("El peso y el precio deben ser un número");
            return;
        }

        if(nombre.equals("") || raza.equals("")|| edad.equals("")|| tipo.equals("") || id.equals("") || peso.equals("")|| fechaIngreso.equals("")|| lugarOrigen.equals("") || genero.equals("") || precio.equals("")|| estado.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de completar todos los campos para poder almacenar una mascota, intentelo de nuevo.");
            return;
        }else {
            char generoChar = genero.charAt(0);
            MascotaDto mascota = new MascotaDto(nombre, raza, Integer.parseInt(edad), tipo, id, Double.parseDouble(peso), fechaIngreso, lugarOrigen, generoChar, Double.parseDouble(precio), estado);
            if (mascotaNegocio.actualizarMascota(mascota)) {
                operacionExitosa("Se ha actualizado la mascota");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar la mascota\n"
                        + "Por favor verifique si la mascota se encuentra registrado");
            }
        }
    }
    
    public void listarMascotas(){
        TablaMascotas tablaMascotas = new TablaMascotas();
        tablaMascotas.llenarTabla();
        tablaMascotas.setVisible(true);
    }
    
    public void operacionExitosa(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void datosErroneos(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
    }

}
