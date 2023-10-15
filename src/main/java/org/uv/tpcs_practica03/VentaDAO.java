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
public class VentaDAO {
    
    private final EntityManager entityManager;

    public VentaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Venta venta) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(venta);
        transaction.commit();
    }

    public Venta read(Long id) {
        return entityManager.find(Venta.class, id);
    }

    public void update(Venta venta) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(venta);
        transaction.commit();
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Venta venta = entityManager.find(Venta.class, id);
        entityManager.remove(venta);
        transaction.commit();
    }

    public List<Venta> findAll() {
        TypedQuery<Venta> query = entityManager.createQuery("SELECT v FROM Venta v", Venta.class);
        return query.getResultList();
    }
}
