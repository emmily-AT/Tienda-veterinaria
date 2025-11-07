package Implement;

import java.util.List;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.ClienteDao;
import Model.ClienteDto;
import Model.MascotaDto;

/**
 *
 * @author emmilyalvarez
 */


public class ClienteDAOFile implements ClienteDao {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String DELIMITADOR_MASCOTAS = ";";
    private static final String FILE_NAME = "cliente.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoClientes;

    public ClienteDAOFile() {
        archivoClientes = new File(FILE_NAME);
    }

    @Override
    public boolean almacenarCliente(ClienteDto cliente) {
        StringBuilder sb = new StringBuilder();

        sb.append(cliente.getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(cliente.getNombres());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(cliente.getDireccionContacto());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(cliente.getNumeroContacto());

        if (cliente.getMascotasACargo() != null && !cliente.getMascotasACargo().isEmpty()) {
            sb.append(DELIMITADOR_MASCOTAS);
            for (MascotaDto m : cliente.getMascotasACargo()) {
                sb.append(m.getId());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(m.getNombre());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(m.getTipo());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(m.getPrecio());
                sb.append(DELIMITADOR_MASCOTAS);
            }
        }

        try {
            escritorArchivo = new FileWriter(archivoClientes, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);
            escritorBuffer.write(sb.toString());
            escritorBuffer.newLine();
            escritorBuffer.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public ClienteDto consultarCliente(String identificacion) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoClientes);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] partes = linea.split(";", 2);
                String[] parametros = partes[0].split(",");

                if (parametros[0].equals(identificacion)) {
                    ClienteDto cliente = new ClienteDto(parametros[0], parametros[1], parametros[2], parametros[3]);

                    ArrayList<MascotaDto> mascotas = new ArrayList<>();

                    if (partes.length > 1 && !partes[1].isEmpty()) {
                        String[] registrosMascotas = partes[1].split(";");
                        for (String registro : registrosMascotas) {
                            if (!registro.isEmpty()) {
                                String[] datos = registro.split(",");
                                if (datos.length >= 4) {
                                    MascotaDto mascota = new MascotaDto();
                                    mascota.setId(datos[0]);
                                    mascota.setNombre(datos[1]);
                                    mascota.setTipo(datos[2]);
                                    mascota.setPrecio(Double.parseDouble(datos[3]));
                                    mascotas.add(mascota);
                                }
                            }
                        }
                    }
                    cliente.setMascotasACargo(mascotas);
                    return cliente;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClienteDto> listarClientes() {
        String linea;
        List<ClienteDto> clientes = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoClientes);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] partes = linea.split(";", 2);
                String[] parametros = partes[0].split(",");

                ClienteDto cliente = new ClienteDto(parametros[0], parametros[1], parametros[2], parametros[3]);

                ArrayList<MascotaDto> mascotas = new ArrayList<>();
                if (partes.length > 1 && !partes[1].isEmpty()) {
                    String[] registrosMascotas = partes[1].split(";");
                    for (String registro : registrosMascotas) {
                        if (!registro.isEmpty()) {
                            String[] datos = registro.split(",");
                            if (datos.length >= 4) {
                                MascotaDto mascota = new MascotaDto();
                                mascota.setId(datos[0]);
                                mascota.setNombre(datos[1]);
                                mascota.setTipo(datos[2]);
                                mascota.setPrecio(Double.parseDouble(datos[3]));
                                mascotas.add(mascota);
                            }
                        }
                    }
                }
                cliente.setMascotasACargo(mascotas);
                clientes.add(cliente);
            }
            return clientes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eliminarCliente(String identificacion) {
        String linea;
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoClientes);
            lectorBuffer = new BufferedReader(lectorArchivo);
            escritorArchivo = new FileWriter(archivoTemporal, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] parametros = linea.split(",");
                if (!parametros[0].equals(identificacion)) {
                    escritorBuffer.write(linea);
                    escritorBuffer.newLine();
                }
            }

            escritorBuffer.close();
            lectorBuffer.close();

            // Reemplazar archivo original
            escritorBuffer = new BufferedWriter(new FileWriter(archivoClientes));
            lectorBuffer = new BufferedReader(new FileReader(archivoTemporal));

            while ((linea = lectorBuffer.readLine()) != null) {
                escritorBuffer.write(linea);
                escritorBuffer.newLine();
            }

            escritorBuffer.close();
            lectorBuffer.close();
            archivoTemporal.delete();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarCliente(ClienteDto cliente) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoClientes);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] parametros = linea.split(",");
                if (parametros[0].equals(cliente.getIdentificacion())) {
                    lectorBuffer.close();
                    lectorArchivo.close();
                    eliminarCliente(cliente.getIdentificacion());
                    almacenarCliente(cliente);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

