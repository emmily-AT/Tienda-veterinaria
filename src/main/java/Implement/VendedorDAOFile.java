package Implement;

import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.VendedorDao;
import Model.VendedorDto;

public class VendedorDAOFile implements VendedorDao {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "vendedorDto.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoVendedores;

    public VendedorDAOFile() {
        archivoVendedores = new File(FILE_NAME);
    }

    @Override
    public boolean almacenarVendedor(VendedorDto vendedor) {
        StringBuilder sb = new StringBuilder();
        sb.append(vendedor.getNombres());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(vendedor.getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(vendedor.getGenero());

        try {

            escritorArchivo = new FileWriter(archivoVendedores, true);
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
    public VendedorDto consultarVendedor(String identificacion) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoVendedores);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[1].equals(identificacion)) {
                    String parametros[] = linea.split(",");
                    return (new VendedorDto(parametros[0], parametros[1],
                            parametros[2].charAt(0)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VendedorDto> listarVendedores() {
        archivoVendedores = new File(FILE_NAME);
        String linea;
        List<VendedorDto> vendedores = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoVendedores);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String parametros[] = linea.split(",");
                vendedores.add(new VendedorDto(parametros[0], parametros[1],
                            parametros[2].charAt(0)));
            }
            return vendedores ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eliminarVendedor(String documento) {
        String linea;
        archivoVendedores = new File(FILE_NAME);
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoVendedores);
            lectorBuffer = new BufferedReader(lectorArchivo);

            escritorArchivo = new FileWriter(archivoTemporal, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[1].equals(documento)) {
                    System.out.println("coincide");
                    escritorBuffer.write("");
                } else {
                    escritorBuffer.write(linea);
                    escritorBuffer.newLine();
                }
            }
            escritorBuffer.close();
            lectorBuffer.close();

            escritorBuffer = new BufferedWriter(new FileWriter(archivoVendedores));
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
    public boolean actualizarVendedor(VendedorDto vendedor) {
        archivoVendedores = new File(FILE_NAME);
        String linea;
        try {
            lectorArchivo = new FileReader(archivoVendedores);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.split(",")[1].equals(vendedor.getIdentificacion())) {
                    lectorBuffer.close();
                    eliminarVendedor(vendedor.getIdentificacion());
                    almacenarVendedor(vendedor);
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
