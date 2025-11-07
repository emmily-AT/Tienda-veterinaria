package Implement;

import java.util.List;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.VacunaDao;
import Model.VacunaDto;

public class VacunaDAOFile implements VacunaDao {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "vacunaDto.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoVacunas;

    public VacunaDAOFile() {
        archivoVacunas = new File(FILE_NAME);
    }

    @Override
    public boolean almacenarVacuna(VacunaDto vacuna) {
        StringBuilder sb = new StringBuilder();
        sb.append(vacuna.getId());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(vacuna.getTipo());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(vacuna.getDescripcion());

        try {

            escritorArchivo = new FileWriter(archivoVacunas, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);
            escritorBuffer.write(sb.toString());
            escritorBuffer.newLine();
            escritorBuffer.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return false;
    }

    @Override
    public VacunaDto consultarVacuna(String id) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoVacunas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[0].equals(id)) {
                    String parametros[] = linea.split(",");
                    return (new VacunaDto(parametros[0], parametros[1], parametros[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VacunaDto> listarVacunas() {
        archivoVacunas = new File(FILE_NAME);
        String linea;
        List<VacunaDto> accesorios = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoVacunas);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String parametros[] = linea.split(",");
                accesorios.add(new VacunaDto(parametros[0], parametros[1], parametros[2]));
            }
            return accesorios ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eliminarVacuna(String id) {
        String linea;
        archivoVacunas = new File(FILE_NAME);
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoVacunas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            escritorArchivo = new FileWriter(archivoTemporal, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[0].equals(id)) {
                    System.out.println("coincide");
                    escritorBuffer.write("");
                } else {
                    escritorBuffer.write(linea);
                    escritorBuffer.newLine();
                }
            }
            escritorBuffer.close();
            lectorBuffer.close();

            escritorBuffer = new BufferedWriter(new FileWriter(archivoVacunas));
            lectorBuffer = new BufferedReader(new FileReader(archivoTemporal));
            while ((linea = lectorBuffer.readLine()) != null) {
                escritorBuffer.write(linea);
                escritorBuffer.newLine();
            }
            escritorBuffer.close();
            lectorBuffer.close();
            System.out.println(archivoTemporal.delete());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (null != lectorArchivo) {
                    lectorArchivo.close();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        return false;
    }

    @Override
    public boolean actualizarVacuna(VacunaDto vacuna) {
        archivoVacunas = new File(FILE_NAME);
        String linea;
        try {
            lectorArchivo = new FileReader(archivoVacunas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[0].equals(vacuna.getId())) {
                    lectorBuffer.close();
                    eliminarVacuna(vacuna.getId());
                    almacenarVacuna(vacuna);
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
