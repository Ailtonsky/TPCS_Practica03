/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcs_practica03;

import java.util.List;
import javax.persistence.*;
import org.uv.tpcs_practica03.Cliente;
/**
 *
 * @author ailton
 */
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;
    
    @Column
    private String fecha;
    
    @Column
    private double total;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetVenta> detallesVenta;

    // Getters y setters

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetVenta> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetVenta> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

   

    
}
