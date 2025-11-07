/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Business.VentaNegocio;
import Business.ClienteNegocio;
import Business.VendedorNegocio;
import Business.MascotaNegocio;
import Business.AccesorioNegocio;

import Model.ClienteDto;
import Model.VendedorDto;
import Model.MascotaDto;
import Model.AccesorioDto;
import Model.Detalle;
import Model.VentaDto;

import Tablas.TablaVentas;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author emmilyalvarez
 */
public class VentaControlador {

    VentaNegocio ventaNegocio = new VentaNegocio();
    ClienteNegocio clienteNegocio = new ClienteNegocio();
    VendedorNegocio vendedorNegocio = new VendedorNegocio();
    MascotaNegocio mascotaNegocio = new MascotaNegocio();
    AccesorioNegocio accesorioNegocio = new AccesorioNegocio();

    public void almacenarVenta(String idVenta, String fechaHora, String precio, String idCliente, String idVendedor, String idMascota, String idAccesorio, String cantidad) {

        if (!idVenta.matches("[0-9]*")) {
            datosErroneos("El ID de la venta debe ser un número.");
            return;
        }
        if (!precio.matches("[0-9.]*")) {
            datosErroneos("El valor total no debe contener letras");
            return;
        }
        if (idCliente.isEmpty() || idVendedor.isEmpty() || precio.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe completar ID Venta, Cliente, Vendedor y Precio para almacenar una venta.");
            return;
        }
        if (idMascota.isEmpty() && idAccesorio.isEmpty()) {
            datosErroneos("Debe añadir al menos una mascota o un accesorio.");
            return;
        }
        if (!idAccesorio.isEmpty() && !cantidad.matches("[0-9]+")) {
            datosErroneos("La cantidad del accesorio debe ser un número entero (mayor a 0).");
            return;
        }

        ClienteDto clienteDto = clienteNegocio.consultarCliente(idCliente);
        if (clienteDto == null) {
            operacionFallida("El id del cliente no coincide con ninguno cliente registrado");
            return;
        }

        VendedorDto vendedorDto = vendedorNegocio.consultarVendedor(idVendedor);
        if (vendedorDto == null) {
            operacionFallida("El id del vendedor no coincide con ninguno vendedor registrado");
            return;
        }

        ArrayList<MascotaDto> mascotasCompradas = new ArrayList<>();
        MascotaDto mascotaDto = null;
        
        if (!idMascota.isEmpty()) {
            mascotaDto = mascotaNegocio.consultarMascota(idMascota);
            if (mascotaDto == null) {
                operacionFallida("El id de la mascota no coincide con ninguna mascota registrada");
                return;
            }
            if (mascotaDto.getEstado().equals("ADOPTADO")) {
                operacionFallida("La mascota ya ha sido adoptada");
                return;
            }           
            mascotasCompradas.add(mascotaDto);
        }

        ArrayList<Detalle> detallesComprados = new ArrayList<>();
        if (!idAccesorio.isEmpty()) {
            AccesorioDto accesorioDto = accesorioNegocio.consultarAccesorio(idAccesorio);
            if (accesorioDto == null) {
                operacionFallida("El id del accesorio no coincide con ninguno accesorio registrado");
                return;
            }

            Detalle detalle = new Detalle();
            detalle.setAccesorio(accesorioDto);
            detalle.setCantidad(Integer.parseInt(cantidad));
            detallesComprados.add(detalle);
        }

        VentaDto venta = new VentaDto(idVenta, fechaHora, Double.parseDouble(precio), clienteDto, vendedorDto);
        venta.setMascotas(mascotasCompradas);
        venta.setDetalles(detallesComprados);

        if (ventaNegocio.almacenarVenta(venta)) {
            ArrayList<MascotaDto> mascotasActuales = clienteDto.getMascotasACargo();
            if (mascotasActuales == null) {
                mascotasActuales = new ArrayList<>();
            }
            mascotasActuales.add(mascotaDto);
            clienteDto.setMascotasACargo(mascotasActuales);
            clienteNegocio.actualizarCliente(clienteDto);

            mascotaDto.setEstado("ADOPTADO");
            mascotaNegocio.actualizarMascota(mascotaDto);

            operacionExitosa("Venta registrada correctamente.");

        } else {

            operacionFallida("Error: No se pudo registrar la venta en el archivo de ventas.");
        }
    }

    public void consultarVenta(String idVenta) {
        if (!idVenta.matches("[0-9]+")) {
            datosErroneos("El ID de la venta debe ser un número.");
            return;
        }
        if (idVenta.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un ID de venta.");
            return;
        }

        VentaDto venta = ventaNegocio.consultarVenta(idVenta);

        if (venta == null) {
            operacionExitosa("El ID ingresado no coincide con ninguna venta registrada.");
        } else {
            // El toString() de VentaDto es muy largo, pero sigue el patrón de tu AccesorioControlador
            operacionExitosa("Venta encontrada:\n\n" + venta.toString());
        }
    }

    public void listarVentas() {
        // Asumiendo que TablaVentas tiene un método para recibir la lista de ventas
        TablaVentas ventana = new TablaVentas();
        ventana.llenarTabla();
        ventana.setVisible(true);
    }

    public void operacionExitosa(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    public void datosErroneos(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
    }

    // Método que usaste en tu borrador
    public void operacionFallida(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operación fallida", JOptionPane.ERROR_MESSAGE);
    }
}
