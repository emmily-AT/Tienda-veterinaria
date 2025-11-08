/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Business;

import Implement.VacunaDAOFile;
import java.util.List;

import Dao.VacunaDao;
import Model.VacunaDto;

/**
 *
 * @author emmilyalvarez
 */

public class VacunaNegocio {

    private final VacunaDao vacunaDAO = new VacunaDAOFile();
    
    public VacunaNegocio(){
        
    }

    public boolean almacenarVacuna(VacunaDto vacuna) {
        //Validaciones de negocio y se ejecutarán los casos de uso de la aplicación
        if(consultarVacuna(vacuna.getId())== null){
            vacunaDAO.almacenarVacuna(vacuna);
            return true;
        }
        return false;
    }

    public List<VacunaDto> listarVacunas() { 
        return vacunaDAO.listarVacunas();
    }
    
    public VacunaDto consultarVacuna(String documento){
        return vacunaDAO.consultarVacuna(documento);
    }
    
    public boolean eliminarVacuna(String documento){
        if(consultarVacuna(documento) != null){
            vacunaDAO.eliminarVacuna(documento);
            return true;
        }
        return false;
    }
    
    public boolean actualizarVacuna(VacunaDto vacuna){
        if(consultarVacuna(vacuna.getId()) != null){
            return vacunaDAO.actualizarVacuna(vacuna);
        }
        return false;
    }


}

