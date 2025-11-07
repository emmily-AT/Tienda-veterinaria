package Dao;

import java.util.List;
import Model.VacunaDto;

public interface VacunaDao {
    public boolean almacenarVacuna(VacunaDto vacuna);
    public VacunaDto consultarVacuna(String id);
    public List<VacunaDto> listarVacunas();
    public boolean eliminarVacuna(String id);
    public boolean actualizarVacuna(VacunaDto vacuna);
}
