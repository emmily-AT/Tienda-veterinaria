package Implement;

import java.util.List;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.AccesorioDao;
import Model.AccesorioDto;

public class AccesorioDAOFile implements AccesorioDao {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "accesorioDto.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoAccesorios;

    public AccesorioDAOFile() {
        archivoAccesorios = new File(FILE_NAME);
    }

    @Override
    public boolean almacenarAccesorio(AccesorioDto accesorio) {
        StringBuilder sb = new StringBuilder();
        sb.append(accesorio.getId());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(accesorio.getTipo());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(accesorio.getDescripcion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(accesorio.getPrecio());

        try {

            escritorArchivo = new FileWriter(archivoAccesorios, true);
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
    public AccesorioDto consultarAccesorio(String identificacion) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoAccesorios);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[0].equals(identificacion)) {
                    String parametros[] = linea.split(",");
                    return (new AccesorioDto(parametros[0], parametros[1],
                            parametros[2], Double.parseDouble(parametros[3])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AccesorioDto> listarAccesorios() {
        archivoAccesorios = new File(FILE_NAME);
        String linea;
        List<AccesorioDto> accesorios = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoAccesorios);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String parametros[] = linea.split(",");
                accesorios.add(new AccesorioDto(parametros[0], parametros[1],
                            parametros[2], Double.parseDouble(parametros[3])));
            }
            return accesorios ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eliminarAccesorio(String documento) {
        String linea;
        archivoAccesorios = new File(FILE_NAME);
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoAccesorios);
            lectorBuffer = new BufferedReader(lectorArchivo);

            escritorArchivo = new FileWriter(archivoTemporal, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[0].equals(documento)) {
                    System.out.println("coincide");
                    escritorBuffer.write("");
                } else {
                    escritorBuffer.write(linea);
                    escritorBuffer.newLine();
                }
            }
            escritorBuffer.close();
            lectorBuffer.close();

            escritorBuffer = new BufferedWriter(new FileWriter(archivoAccesorios));
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
    public boolean actualizarAccesorio(AccesorioDto accesorio) {
        archivoAccesorios = new File(FILE_NAME);
        String linea;
        try {
            lectorArchivo = new FileReader(archivoAccesorios);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[0].equals(accesorio.getId())) {
                    lectorBuffer.close();
                    eliminarAccesorio(accesorio.getId());
                    almacenarAccesorio(accesorio);
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
