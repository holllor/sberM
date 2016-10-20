/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.sberm2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author олег
 */
public class DAOtrans {

    public void workDb() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("sber1");

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        //  entitymanager.persist(  );
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }
    
}
