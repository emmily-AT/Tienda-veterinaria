package Business;

import Implement.MascotaDAOFile;
import java.util.List;
import Dao.MascotaDao;
import Model.MascotaDto;

public class MascotaNegocio {

    private final MascotaDao mascotaDAO = new MascotaDAOFile();
    
    public MascotaNegocio(){
        
    }

    public boolean almacenarMascota(MascotaDto mascota) {
        //Validaciones de negocio y se ejecutarán los casos de uso de la aplicación
        if(consultarMascota(mascota.getId())== null){
            mascotaDAO.almacenarMascota(mascota);
            return true;
        }
        return false;
    }

    public List<MascotaDto> listarMascotas() { //listarPersonas
        return mascotaDAO.listarMascotas();
    }
    
    public MascotaDto consultarMascota(String documento){
        return mascotaDAO.consultarMascota(documento);
    }
    
    public boolean eliminarMascota(String documento){
        if(consultarMascota(documento) != null){
            mascotaDAO.eliminarMascota(documento);
            return true;
        }
        return false;
    }
    
    public boolean actualizarMascota(MascotaDto mascota){
        if(consultarMascota(mascota.getId()) != null){
            return mascotaDAO.actualizarMascota(mascota);
        }
        return false;
    }
}
