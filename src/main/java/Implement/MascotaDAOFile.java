/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implement;

import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.MascotaDao;
import Model.Consulta;
import Model.Dosis;
import Model.MascotaDto;
import Model.VacunaDto;

/**
 *
 * @author emmilyalvarez
 */
public class MascotaDAOFile implements MascotaDao {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String DELIMITADOR_SECCIONES = ";";
    private static final String DELIMITADOR_DATOS = "|";
    private static final String FILE_NAME = "mascota.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoMascota;

    public MascotaDAOFile() {
        archivoMascota = new File(FILE_NAME);
    }

    @Override
    public boolean almacenarMascota(MascotaDto mascota) {
        StringBuilder sb = new StringBuilder();
        sb.append(mascota.getNombre());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getRaza());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getEdad());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getTipo());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getId());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getPeso());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getFechaIngreso());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getLugarOrigen());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getGenero());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getPrecio());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getEstado());

        if (mascota.getConsultas() != null && !mascota.getConsultas().isEmpty()) {
            sb.append(DELIMITADOR_SECCIONES);
            for (Consulta c : mascota.getConsultas()) {
                sb.append(c.getFechaHora());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(c.getSintomas());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(c.getTratamiento());
                sb.append(DELIMITADOR_DATOS);

            }
        }

        if (mascota.getVacunas() != null && !mascota.getVacunas().isEmpty()) {
            sb.append(DELIMITADOR_SECCIONES);
            for (Dosis dosis : mascota.getVacunas()) {
                sb.append(dosis.getVacuna().getTipo());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(dosis.getVacuna().getId());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(dosis.getFecha());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(dosis.getCantidad());
                sb.append(DELIMITADOR_DATOS);
            }
        }

        try {

            escritorArchivo = new FileWriter(archivoMascota, true);
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
    public MascotaDto consultarMascota(String identificacion) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoMascota);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] secciones = linea.split(";");
                String[] datosMascota = secciones[0].split(",");

                if (datosMascota[4].equals(identificacion)) {
                    MascotaDto mascota = new MascotaDto(datosMascota[0], datosMascota[1], Integer.parseInt(datosMascota[2]), datosMascota[3],
                            datosMascota[4], Double.parseDouble(datosMascota[5]), datosMascota[6], datosMascota[7],
                            datosMascota[8].charAt(0), Double.parseDouble(datosMascota[9]), datosMascota[10]);

                    ArrayList<Consulta> consultas = new ArrayList<>();
                    if (secciones.length > 1 && !secciones[1].isEmpty()) {
                        String[] consultasArc = secciones[1].split("\\|");
                        for (String consulta : consultasArc) {
                            if (!consulta.isBlank()) {
                                String[] datosConsulta = consulta.split(",");
                                consultas.add(new Consulta(datosConsulta[0], datosConsulta[1], datosConsulta[2]
                                ));
                            }
                        }
                        mascota.setConsultas(consultas);
                    }

                    ArrayList<Dosis> vacunas = new ArrayList<>();
                    if (secciones.length > 2 && !secciones[2].isEmpty()) {
                        String[] vacunasArc = secciones[2].split("\\|");
                        for (String vacuna : vacunasArc) {
                            if (!vacuna.isBlank()) {
                                String[] datosVacuna = vacuna.split(",");
                                VacunaDto vacunaDto = new VacunaDto();
                                vacunaDto.setTipo(datosVacuna[0]);
                                vacunaDto.setId(datosVacuna[1]);

                                Dosis dosis = new Dosis(datosVacuna[2], Double.parseDouble(datosVacuna[3]), vacunaDto);

                                vacunas.add(dosis);
                            }
                        }
                        mascota.setVacunas(vacunas);
                    }

                    return mascota;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MascotaDto> listarMascotas() {
        archivoMascota = new File(FILE_NAME);
        String linea;
        List<MascotaDto> mascotas = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoMascota);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String[] secciones = linea.split(";");
                String[] datosMascota = secciones[0].split(",");

                MascotaDto mascota = new MascotaDto(datosMascota[0], datosMascota[1], Integer.parseInt(datosMascota[2]), datosMascota[3],
                        datosMascota[4], Double.parseDouble(datosMascota[5]), datosMascota[6], datosMascota[7],
                        datosMascota[8].charAt(0), Double.parseDouble(datosMascota[9]), datosMascota[10]);

                ArrayList<Consulta> consultas = new ArrayList<>();
                if (secciones.length > 1 && !secciones[1].isEmpty()) {
                    String[] consultasArc = secciones[1].split("\\|");
                    for (String consulta : consultasArc) {
                        if (!consulta.isBlank()) {
                            String[] datosConsulta = consulta.split(",");
                            consultas.add(new Consulta(datosConsulta[0], datosConsulta[1], datosConsulta[2]
                            ));
                        }
                    }
                    mascota.setConsultas(consultas);
                }

                ArrayList<Dosis> vacunas = new ArrayList<>();
                if (secciones.length > 2 && !secciones[2].isEmpty()) {
                    String[] vacunasArc = secciones[2].split("\\|");
                    for (String vacuna : vacunasArc) {
                        if (!vacuna.isBlank()) {
                            String[] datosVacuna = vacuna.split(",");
                            VacunaDto vacunaDto = new VacunaDto();
                            vacunaDto.setTipo(datosVacuna[0]);
                            vacunaDto.setTipo(datosVacuna[1]);

                            Dosis dosis = new Dosis(datosVacuna[2], Double.parseDouble(datosVacuna[3]), vacunaDto);

                            vacunas.add(dosis);
                        }
                    }
                    mascota.setVacunas(vacunas);
                }

                mascotas.add(mascota);
            }
            return mascotas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eliminarMascota(String documento) {
        String linea;
        archivoMascota = new File(FILE_NAME);
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoMascota);
            lectorBuffer = new BufferedReader(lectorArchivo);

            escritorArchivo = new FileWriter(archivoTemporal);
            escritorBuffer = new BufferedWriter(escritorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] partes = linea.split(";");
                String[] parametros = partes[0].split(",");

                if (parametros[4].equals(documento)) {
                    System.out.println("coincide");
                    escritorBuffer.write("");
                } else {
                    escritorBuffer.write(linea);
                    escritorBuffer.newLine();
                }
            }
            escritorBuffer.close();
            lectorBuffer.close();

            escritorBuffer = new BufferedWriter(new FileWriter(archivoMascota));
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
    public boolean actualizarMascota(MascotaDto mascota) {
        archivoMascota = new File(FILE_NAME);
        String linea;
        try {
            lectorArchivo = new FileReader(archivoMascota);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {

                String[] partes = linea.split(";");
                String[] parametros = partes[0].split(",");

                if (parametros[4].equals(mascota.getId())) {

                    //Si la mascota viene sin consultas o vacunas, las recuperamos del archivo
                    if ((mascota.getConsultas() == null || mascota.getConsultas().isEmpty())
                            && partes.length > 1 && !partes[1].isEmpty()) {

                        ArrayList<Consulta> consultas = new ArrayList<>();
                        String[] consultasArc = partes[1].split("\\|");
                        for (String consulta : consultasArc) {
                            if (!consulta.isBlank()) {
                                String[] datosConsulta = consulta.split(",");
                                consultas.add(new Consulta(datosConsulta[0], datosConsulta[1], datosConsulta[2]));
                            }
                        }
                        mascota.setConsultas(consultas);
                    }

                    if ((mascota.getVacunas() == null || mascota.getVacunas().isEmpty())
                            && partes.length > 2 && !partes[2].isEmpty()) {

                        ArrayList<Dosis> vacunas = new ArrayList<>();
                        String[] vacunasArc = partes[2].split("\\|");
                        for (String vacuna : vacunasArc) {
                            if (!vacuna.isBlank()) {
                                String[] datosVacuna = vacuna.split(",");
                                VacunaDto vacunaDto = new VacunaDto();
                                vacunaDto.setTipo(datosVacuna[0]);
                                vacunaDto.setId(datosVacuna[1]);

                                Dosis dosis = new Dosis(datosVacuna[2], Double.parseDouble(datosVacuna[3]), vacunaDto);
                                vacunas.add(dosis);
                            }
                        }
                        mascota.setVacunas(vacunas);
                    }

                    lectorBuffer.close();
                    lectorArchivo.close();

                    eliminarMascota(mascota.getId());
                    almacenarMascota(mascota);
                    return true;
                }
            }
            lectorBuffer.close();
            lectorArchivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
