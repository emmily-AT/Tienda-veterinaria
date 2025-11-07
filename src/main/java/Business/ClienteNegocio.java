package Business;

import Implement.ClienteDAOFile;
import java.util.List;

import Dao.ClienteDao;
import Model.ClienteDto;


public class ClienteNegocio {

    private final ClienteDao clienteDAO = new ClienteDAOFile();
    
    public ClienteNegocio(){
        
    }

    public boolean almacenarCliente(ClienteDto cliente) {
        //Validaciones de negocio y se ejecutarán los casos de uso de la aplicación
        if(consultarCliente(cliente.getIdentificacion())== null){
            clienteDAO.almacenarCliente(cliente);
            return true;
        }
        return false;
    }

    public List<ClienteDto> listarClientes() { //listarPersonas
        return clienteDAO.listarClientes();
    }
    
    public ClienteDto consultarCliente(String documento){
        return clienteDAO.consultarCliente(documento);
    }
    
    public boolean eliminarCliente(String documento){
        if(consultarCliente(documento) != null){
            clienteDAO.eliminarCliente(documento);
            return true;
        }
        return false;
    }
    
    public boolean actualizarCliente(ClienteDto cliente){
        if(consultarCliente(cliente.getIdentificacion()) != null){
            return clienteDAO.actualizarCliente(cliente);
        }
        return false;
    }


}
