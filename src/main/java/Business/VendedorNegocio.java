package Business;

import Implement.VendedorDAOFile;
import java.util.List;

import Dao.VendedorDao;
import Model.VendedorDto;


public class VendedorNegocio {

    private final VendedorDao vendedorDAO = new VendedorDAOFile();
    
    public VendedorNegocio(){
        
    }

    public boolean almacenarVendedor(VendedorDto vendedor) {
        //Validaciones de negocio y se ejecutarán los casos de uso de la aplicación
        if(consultarVendedor(vendedor.getIdentificacion())== null){
            vendedorDAO.almacenarVendedor(vendedor);
            return true;
        }
        return false;
    }

    public List<VendedorDto > listarVendedores() { //listarPersonas
        return vendedorDAO.listarVendedores();
    }
    
    public VendedorDto consultarVendedor(String documento){
        return vendedorDAO.consultarVendedor(documento);
    }
    
    public boolean eliminarVendedor(String documento){
        if(consultarVendedor(documento) != null){
            vendedorDAO.eliminarVendedor(documento);
            return true;
        }
        return false;
    }
    
    public boolean actualizarVendedor(VendedorDto  vendedor){
        if(consultarVendedor(vendedor.getIdentificacion()) != null){
            return vendedorDAO.actualizarVendedor(vendedor);
        }
        return false;
    }


}
