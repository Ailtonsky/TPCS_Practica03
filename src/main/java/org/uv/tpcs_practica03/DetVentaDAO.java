/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcs_practica03;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author ailton
 */
public class DetVentaDAO {
    
    private final EntityManager entityManager;

    public DetVentaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(DetVenta detVenta) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(detVenta);
        transaction.commit();
    }

    public DetVenta read(Long id) {
        return entityManager.find(DetVenta.class, id);
    }

    public void update(DetVenta detVenta) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(detVenta);
        transaction.commit();
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        DetVenta detVenta = entityManager.find(DetVenta.class, id);
        entityManager.remove(detVenta);
        transaction.commit();
    }

    public List<DetVenta> findAll() {
        TypedQuery<DetVenta> query = entityManager.createQuery("SELECT d FROM DetVenta d", DetVenta.class);
        return query.getResultList();
    }
    
    public List<DetVenta> obtenerDetallesVentaPorVentaCliente(Long idVenta, Long idCliente) {
    TypedQuery<DetVenta> query = entityManager.createQuery(
        "SELECT d FROM DetVenta d WHERE d.venta.idVenta = :ventaId AND d.venta.cliente.idCliente = :clienteId",
        DetVenta.class
    );
    query.setParameter("ventaId", idVenta);
    query.setParameter("clienteId", idCliente);
    return query.getResultList();
}


}
