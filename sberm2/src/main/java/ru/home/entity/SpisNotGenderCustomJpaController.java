/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ru.home.entity.exceptions.NonexistentEntityException;

/**
 *
 * @author олег
 */
public class SpisNotGenderCustomJpaController implements Serializable {

    public SpisNotGenderCustomJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SpisNotGenderCustom spisNotGenderCustom) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(spisNotGenderCustom);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SpisNotGenderCustom spisNotGenderCustom) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            spisNotGenderCustom = em.merge(spisNotGenderCustom);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = spisNotGenderCustom.getId();
                if (findSpisNotGenderCustom(id) == null) {
                    throw new NonexistentEntityException("The spisNotGenderCustom with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SpisNotGenderCustom spisNotGenderCustom;
            try {
                spisNotGenderCustom = em.getReference(SpisNotGenderCustom.class, id);
                spisNotGenderCustom.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The spisNotGenderCustom with id " + id + " no longer exists.", enfe);
            }
            em.remove(spisNotGenderCustom);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SpisNotGenderCustom> findSpisNotGenderCustomEntities() {
        return findSpisNotGenderCustomEntities(true, -1, -1);
    }

    public List<SpisNotGenderCustom> findSpisNotGenderCustomEntities(int maxResults, int firstResult) {
        return findSpisNotGenderCustomEntities(false, maxResults, firstResult);
    }

    private List<SpisNotGenderCustom> findSpisNotGenderCustomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SpisNotGenderCustom.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * по заданному ид пользователя возвращает код мсс и средний показатель для
     * него
     *
     * @param id
     * @return
     */
    public List<List<String>> selectSrednCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            try {
                Connection connection = em.unwrap(Connection.class);

                PreparedStatement pstm = connection.prepareStatement("SELECT  t.mcc_code, round(sum(t.amount)/count(t.mcc_code)*-1) as sredn "
                        + "  FROM transactions t\n"
                        + "left join customers_gender_train g on  t.customer_id= g.customer_id\n"
                        + "  where g.gender is null  and t.customer_id = ? and t.amount <0\n"
                        + "  and t.tr_type in (1010,1110,1030,1110,1200,2010,2011,2330,2370,2371,4010,4071,4110,6110,7010,\n"
                        + "  7011,7030,7031,7070,7071) and t.mcc_code in (1711,1731,2741,3351,3501,4784,4899,5013,5039,5044,5072,5074,"
                        + "5422,5511,5532,5533,5541,5542,5813,5816,5940,5967,5968,5983,6211,6513,7011,7278,7299,"
                        + "7372,7512,7531,7538,7933,7993,7994,7995,8220,8244,8398,8699,9402) \n"
                        + "  group by t.mcc_code \n"
                        + "  order by  t.mcc_code");
                pstm.setInt(1, id);
                ResultSet rs = pstm.executeQuery();
                List<List<String>> listData = new ArrayList<>();
                while (rs.next()) {
                    List<String> listRow = new ArrayList<>();
                    listRow.add(String.valueOf(rs.getInt("mcc_code")));
                    listRow.add(String.valueOf(rs.getInt("sredn")));
                    listData.add(listRow);
                }
                em.getTransaction().commit();
                return listData;
            } catch (SQLException ex) {
                Logger.getLogger(SpisNotGenderCustomJpaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            em.close();
        }
        return null;
    }

    public SpisNotGenderCustom findSpisNotGenderCustom(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SpisNotGenderCustom.class, id);
        } finally {
            em.close();
        }
    }
// SrednNeopr

    public List<SrednNeopr> findSpisSrednNeoprEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SrednNeopr.class));
            Query q = em.createQuery(cq);

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getSpisNotGenderCustomCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SpisNotGenderCustom> rt = cq.from(SpisNotGenderCustom.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
