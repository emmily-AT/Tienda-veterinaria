package Dao;

import java.util.List;
import Model.AccesorioDto;

public interface AccesorioDao {
    public boolean almacenarAccesorio(AccesorioDto accesorio);
    public AccesorioDto consultarAccesorio(String id);
    public List<AccesorioDto> listarAccesorios();
    public boolean eliminarAccesorio(String id);
    public boolean actualizarAccesorio(AccesorioDto accesorio);
}
