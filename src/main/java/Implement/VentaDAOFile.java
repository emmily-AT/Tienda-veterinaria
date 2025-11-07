package Implement;

import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.VentaDao;
import Model.AccesorioDto;
import Model.ClienteDto;
import Model.Detalle;
import Model.MascotaDto;
import Model.VendedorDto;
import Model.VentaDto;

public class VentaDAOFile implements VentaDao {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String DELIMITADOR_SECCIONES = ";";
    private static final String DELIMITADOR_DATOS = "|";

    private static final String FILE_NAME = "ventaDto.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoVentas;

    public VentaDAOFile() {
        archivoVentas = new File(FILE_NAME);
    }

    @Override
    public boolean almacenarVenta(VentaDto venta) {
        StringBuilder sb = new StringBuilder();

        sb.append(venta.getIdVenta());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getFechaHora());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getValorVenta());

        sb.append(DELIMITADOR_SECCIONES);

        sb.append(venta.getCliente().getNombres());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getCliente().getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getCliente().getDireccionContacto());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getCliente().getNumeroContacto());

        sb.append(DELIMITADOR_SECCIONES);

        sb.append(venta.getVendedor().getNombres());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getVendedor().getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getVendedor().getGenero());

        sb.append(DELIMITADOR_SECCIONES);

        //lista mascotas
        if (venta.getMascotas() != null && !venta.getMascotas().isEmpty()) {
            for (MascotaDto mascota : venta.getMascotas()) {
                sb.append(mascota.getNombre());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(mascota.getId());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(mascota.getTipo());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(mascota.getPrecio());
                sb.append(DELIMITADOR_DATOS);

            }
        }

        sb.append(DELIMITADOR_SECCIONES);

        // Lista de detalles
        if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
            for (Detalle detalle : venta.getDetalles()) {
                sb.append(detalle.getAccesorio().getTipo());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(detalle.getAccesorio().getId());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(detalle.getAccesorio().getDescripcion());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(detalle.getAccesorio().getPrecio());
                sb.append(DELIMITADOR_ARCHIVO);
                sb.append(detalle.getCantidad());
                sb.append(DELIMITADOR_DATOS);
            }
        }

        try {
            escritorArchivo = new FileWriter(archivoVentas, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);
            escritorBuffer.write(sb.toString());
            escritorBuffer.newLine();
            escritorBuffer.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar la venta: " + e.getMessage());
        }
        return false;
    }

    @Override
    public VentaDto consultarVenta(String idVentaBuscada) {
        String linea;

        try {
            lectorArchivo = new FileReader(archivoVentas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] partes = linea.split(";");
                String[] datosVenta = partes[0].split(",");

                if (datosVenta[0].equals(idVentaBuscada)) {
                    String idVenta = datosVenta[0];
                    String fechaHora = datosVenta[1];
                    String valorVenta = datosVenta[2];

                    String[] datosCliente = partes[1].split(",");
                    ClienteDto cliente = new ClienteDto();
                    cliente.setNombres(datosCliente[0]);
                    cliente.setIdentificacion(datosCliente[1]);
                    cliente.setDireccionContacto(datosCliente[2]);
                    cliente.setNumeroContacto(datosCliente[3]);

                    String[] datosVendedor = partes[2].split(",");
                    VendedorDto vendedor = new VendedorDto();
                    vendedor.setNombres(datosVendedor[0]);
                    vendedor.setIdentificacion(datosVendedor[1]);
                    vendedor.setGenero(datosVendedor[2].charAt(0));

                    VentaDto venta = new VentaDto(idVenta, fechaHora, Double.parseDouble(valorVenta), cliente, vendedor);

                    ArrayList<MascotaDto> mascotas = new ArrayList<>();
                    if (partes.length > 3 && !partes[3].isEmpty()) {
                        String[] listaMascotas = partes[3].split("\\|");
                        for (String mascotaArc : listaMascotas) {
                            if (!mascotaArc.isEmpty()) {
                                String[] datosMascota = mascotaArc.split(",");
                                MascotaDto mascota = new MascotaDto();
                                mascota.setNombre(datosMascota[0]);
                                mascota.setId(datosMascota[1]);
                                mascota.setTipo(datosMascota[2]);
                                mascota.setPrecio(Double.parseDouble(datosMascota[3]));
                                mascotas.add(mascota);

                            }
                        }
                        venta.setMascotas(mascotas);
                    }

                    ArrayList<Detalle> detalles = new ArrayList<>();
                    if (partes.length > 4 && !partes[4].isEmpty()) {
                        String[] listaDetalles = partes[4].split("\\|");
                        for (String detalleArc : listaDetalles) {
                            if (!detalleArc.isEmpty()) {
                                String[] datosAccesorio = detalleArc.split(",");

                                AccesorioDto accesorio = new AccesorioDto();
                                accesorio.setTipo(datosAccesorio[0]);
                                accesorio.setId(datosAccesorio[1]);
                                accesorio.setDescripcion(datosAccesorio[2]);
                                accesorio.setPrecio(Double.parseDouble(datosAccesorio[3]));

                                int cantidad = Integer.parseInt(datosAccesorio[4]);

                                Detalle detalle = new Detalle();
                                detalle.setAccesorio(accesorio);
                                detalle.setCantidad(cantidad);

                                detalles.add(detalle);

                            }
                        }
                        venta.setDetalles(detalles);
                    }

                    return venta;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VentaDto> listarVentas() {
        archivoVentas = new File(FILE_NAME);
        String linea;
        List<VentaDto> ventas = new ArrayList<>();

        try {
            lectorArchivo = new FileReader(archivoVentas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                String[] partes = linea.split(";");
                String[] datosVenta = partes[0].split(",");

                String idVenta = datosVenta[0];
                String fechaHora = datosVenta[1];
                String valorVenta = datosVenta[2];

                String[] datosCliente = partes[1].split(",");
                ClienteDto cliente = new ClienteDto();
                cliente.setNombres(datosCliente[0]);
                cliente.setIdentificacion(datosCliente[1]);
                cliente.setDireccionContacto(datosCliente[2]);
                cliente.setNumeroContacto(datosCliente[3]);

                String[] datosVendedor = partes[2].split(",");
                VendedorDto vendedor = new VendedorDto();
                vendedor.setNombres(datosVendedor[0]);
                vendedor.setIdentificacion(datosVendedor[1]);
                vendedor.setGenero(datosVendedor[2].charAt(0));

                VentaDto venta = new VentaDto(idVenta, fechaHora, Double.parseDouble(valorVenta), cliente, vendedor);

                ArrayList<MascotaDto> mascotas = new ArrayList<>();
                if (partes.length > 3 && !partes[3].isEmpty()) {
                    String[] listaMascotas = partes[3].split("\\|");
                    for (String mascotaArc : listaMascotas) {
                        if (!mascotaArc.isEmpty()) {
                            String[] datosMascota = mascotaArc.split(",");
                            MascotaDto mascota = new MascotaDto();
                            mascota.setNombre(datosMascota[0]);
                            mascota.setId(datosMascota[1]);
                            mascota.setTipo(datosMascota[2]);
                            mascota.setPrecio(Double.parseDouble(datosMascota[3]));
                            mascotas.add(mascota);

                        }
                    }
                    venta.setMascotas(mascotas);
                }

                ArrayList<Detalle> detalles = new ArrayList<>();
                if (partes.length > 4 && !partes[4].isEmpty()) {
                    String[] listaDetalles = partes[4].split("\\|");
                    for (String detalleArc : listaDetalles) {
                        if (!detalleArc.isEmpty()) {
                            String[] datosAccesorio = detalleArc.split(",");

                            AccesorioDto accesorio = new AccesorioDto();
                            accesorio.setTipo(datosAccesorio[0]);
                            accesorio.setId(datosAccesorio[1]);
                            accesorio.setDescripcion(datosAccesorio[2]);
                            accesorio.setPrecio(Double.parseDouble(datosAccesorio[3]));

                            int cantidad = Integer.parseInt(datosAccesorio[4]);

                            Detalle detalle = new Detalle();
                            detalle.setAccesorio(accesorio);
                            detalle.setCantidad(cantidad);

                            detalles.add(detalle);                           
                        }
                    }
                    venta.setDetalles(detalles);
                }
                
                ventas.add(venta);

                }
                return ventas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
