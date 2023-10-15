/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.tpcs_practica03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uv.tpcs_practica03.*;
/**
 *
 * @author ailton
 */
public class TPCS_Practica03 {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            // Inicializamos los DAO con la sesión de Hibernate
            ClienteDAO clienteDAO = new ClienteDAO(session);
            VentaDAO ventaDAO = new VentaDAO(session);
            DetVentaDAO detVentaDAO = new DetVentaDAO(session);
            ProductoDAO productoDAO = new ProductoDAO(session);

            // Ejemplo de inserción de una nueva entidad Cliente
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre("Jose Tioba");
            nuevoCliente.setRfc("JKZ123456789");
            clienteDAO.create(nuevoCliente);

            // Ejemplo de inserción de una nueva entidad Venta con un DetVenta asociado
            Venta nuevaVenta = new Venta();
            nuevaVenta.setFecha("2023-12-11");
            nuevaVenta.setTotal(550.0);
            nuevaVenta.setCliente(nuevoCliente);
            ventaDAO.create(nuevaVenta);


            // Ejemplo de inserción de un nuevo Producto
            Producto nuevoProducto = new Producto();
            nuevoProducto.setDescripcion("Coca Cola");
            nuevoProducto.setPrecio(200.0);
            nuevoProducto.setExistencia(15);
            nuevoProducto.setCosto(50.0);
            productoDAO.create(nuevoProducto);

            // Ejemplo de actualización de un cliente
            Cliente clienteExistente = clienteDAO.read(1L);
            clienteExistente.setNombre("Jose Perez"); // Actualizamos el nombre
            clienteDAO.update(clienteExistente);

            // Ejemplo de actualización de una venta
            Venta ventaExistente = ventaDAO.read(1L);
            ventaExistente.setTotal(600.0); // Actualizamos el total de la venta
            ventaDAO.update(ventaExistente);

            // Ejemplo de eliminación de un producto
            //Producto productoAEliminar = productoDAO.read(2L); // Suponemos que el producto con ID 2 existe
            //productoDAO.delete(productoAEliminar.getIdProducto());
            
            DetVenta nuevoDetVenta = new DetVenta();
            nuevoDetVenta.setVenta(nuevaVenta);
            nuevoDetVenta.setProducto(productoDAO.read(1L)); // Suponemos que el producto con ID 1 existe
            nuevoDetVenta.setCantidad(2);
            nuevoDetVenta.setDescripcion("Producto 1");
            nuevoDetVenta.setPrecio(250.0);
            detVentaDAO.create(nuevoDetVenta);

            // Ejemplo de búsqueda de un detalle de venta
            DetVenta detalleVenta = detVentaDAO.read(1L); // Suponemos que el detalle de venta con ID 1 existe
            System.out.println("Detalle de Venta - ID: " + detalleVenta.getIdDetVenta());
            System.out.println("Venta: " + detalleVenta.getVenta().getIdVenta());
            System.out.println("Producto: " + detalleVenta.getProducto().getIdProducto());
            System.out.println("Cantidad: " + detalleVenta.getCantidad());
            System.out.println("Descripción: " + detalleVenta.getDescripcion());
            System.out.println("Precio: " + detalleVenta.getPrecio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
