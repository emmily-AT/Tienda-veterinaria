/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Business;

import Implement.AccesorioDAOFile;
import java.util.List;

import Dao.AccesorioDao;
import Model.AccesorioDto;

/**
 *
 * @author emmilyalvarez
 */

public class AccesorioNegocio {

    private final AccesorioDao accesorioDAO = new AccesorioDAOFile();
    
    public AccesorioNegocio(){
        
    }

    public boolean almacenarAccesorio(AccesorioDto accesorio) {
        //Validaciones de negocio y se ejecutarán los casos de uso de la aplicación
        if(consultarAccesorio(accesorio.getId())== null){
            accesorioDAO.almacenarAccesorio(accesorio);
            return true;
        }
        return false;
    }

    public List<AccesorioDto> listarAccesorios() { //listarPersonas
        return accesorioDAO.listarAccesorios();
    }
    
    public AccesorioDto consultarAccesorio(String documento){
        return accesorioDAO.consultarAccesorio(documento);
    }
    
    public boolean eliminarAccesorio(String documento){
        if(consultarAccesorio(documento) != null){
            accesorioDAO.eliminarAccesorio(documento);
            return true;
        }
        return false;
    }
    
    public boolean actualizarAccesorio(AccesorioDto accesorio){
        if(consultarAccesorio(accesorio.getId()) != null){
            return accesorioDAO.actualizarAccesorio(accesorio);
        }
        return false;
    }


}
