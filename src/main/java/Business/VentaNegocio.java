package Business;

import Implement.VentaDAOFile;
import java.util.List;

import Dao.VentaDao;
import Model.VentaDto;

public class VentaNegocio {

    private final VentaDao ventaDAO = new VentaDAOFile();

    public VentaNegocio() {
    }

    public boolean almacenarVenta(VentaDto venta) {
        //Validaciones de negocio y se ejecutarán los casos de uso de la aplicación
        if (consultarVenta(venta.getIdVenta()) == null) {
            ventaDAO.almacenarVenta(venta);
            return true;
        }
        return false;
    }
    
    public VentaDto consultarVenta(String id) {
        return ventaDAO.consultarVenta(id);
    }

    public List<VentaDto> listarVentas() {
        return ventaDAO.listarVentas();
    }
}
